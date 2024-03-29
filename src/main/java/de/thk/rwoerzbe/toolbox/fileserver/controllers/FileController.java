package de.thk.rwoerzbe.toolbox.fileserver.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.core.io.ByteArrayResource;

import jakarta.servlet.http.HttpServletRequest;

/**
 * This controller serves files relative to the user.dir
 * where the application has been started.
 * 
 * For security reasons, the file server only works if the
 * application has been started with profile "standalone".
 */
@RestController
public class FileController {

    @Autowired
    private Environment environment;

    /**
     * Returns the contents of a text file. The content type of that file
     * is guessed with {@link java.net.URLConnection#guessContentTypeFromName(String fname)}.
     * For security reasons, the file must be located beneath the <code>user.dir</code> where the server was started
     * and the active profile has to be <code>standalone</code>.
     * @param request The HTTP requests containing a path in the request URL 
     * like <code>/files/path/to/myfile.txt</code> that looks up a file <code>path/to/myfile.txt</code> in the <code>user.dir</code>
     * @return The files contents.
     */
    @GetMapping("/files/**")
    public ResponseEntity<Resource> getFile(HttpServletRequest request) {

        if (Arrays.asList(environment.getActiveProfiles()).contains("standalone")) {
            // Extract path after /files/
            String filePath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            filePath = filePath.replaceFirst("/files/", "");

            String currentDirectory = System.getProperty("user.dir");
            File file = new File(currentDirectory, filePath);

            boolean fileInCurrentDirectory;

            try {
                fileInCurrentDirectory = Paths.get(file.getPath()).startsWith(Paths.get(currentDirectory).toRealPath());
            } catch (IOException e) {
                fileInCurrentDirectory = false;
            }

            if (!file.exists() || file.isDirectory() || !fileInCurrentDirectory) {
                return ResponseEntity.notFound().build();
            }

            // Determine Content-Type
            String contentType = URLConnection.guessContentTypeFromName(file.getName());
            if (contentType == null) {
                contentType = "application/octet-stream"; // default if type can't be determined
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));

            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .body(resource);
        } else {
            return ResponseEntity.internalServerError().body(new ByteArrayResource(
                    "<html><body>Files can only be served if Toolbox is started with profile 'standalone'".getBytes()));
        }
    }

}
