#!/bin/bash

## INTRODUCTION

# Jeder der folgenden Kommentare fordert Sie auf, genau eine Kommandozeile als Lösung zu formulieren.
# Mitunter müssen hierbei jedoch mehrere Kommandos mittels Pipe verkettet werden.
# Redirections in oder aus Dateien sowie Expansionen sind mitunter auch zur Lösung nötig.
# Lösen Sie die Aufgaben in einer Bash-Shell, vorzugsweise in der die Google Cloud Shell


# Each of the following comments asks you to formulate exactly one command line as a solution.
# Sometimes, however, several commands must be concatenated by means of pipe.
# Redirections in or from files as well as expansions are sometimes also necessary for the solution.
# Solve the tasks in a Bash shell, preferrably the Google Cloud Shell.


## FILES AND DIRECTORIES

# Erzeugen Sie im Homeverzeichnis ein Verzeichnis devops und darin ein Verzeichnis clbasics
# Create a directory devops in the home directory and a directory clbasics in it

mkdir -p ~/devops/clbasics

# Wechseln Sie in diese neuerstellte Verzeichnis. Verwenden Sie die Tabulatortaste, um nicht alle Pfadbestandteile komplett eingeben zu müssen.
# Change to this newly created directory. Use the TAB key such that you do not have to type in all path segments by hand.

cd ~/devops/clbasics

# Erstellen Sie eine LEERE Datei namens clbasics.txt
# Create an EMPTY file named clbasics.txt

touch clbasics.txt

# Fügen Sie den String "This is the first line" in die Datei ein
# insert the string "This is the first line" into the file

echo "This is the first line" > clbasics.txt

# Fügen Sie den String "This is the second line" in die Datei ein
# Insert the string "This is the second line" into the file

echo "This is the second line" > clbasics.txt

# Geben Sie den Inhalt der Datei aus
# Output the contents of the file
 
cat clbasics.txt

# Erstellen Sie ein Unterverzeichnis names subdir und darin ein Unterverzeichnis subsubdir mit EINEM Kommando
# Create a subdirectory named subdir and in it a subdirectory subsubdir with ONE command

mkdir -p subdir/subsubdir

# Kopieren Sie clbasics.txt unter gleichem Namen in subdir
# copy clbasics.txt under the same name into subdir

cp clbasics.txt subdir/

# Verschieben Sie subdir/clbasics.txt in das Verzeichnis subsubdir
# Move subdir/clbasics.txt to the directory subsubdir

mv subdir/clbasics.txt subdir/subsubdir/

# Entfernen Sie das Verzeichnis subdir und alle Unterverzeichnisse und Dateien mit einem Kommando (ACHTUNG: GEFÄHRLICH)
# Remove the directory subdir and all subdirectories and files with a command (CAUTION: DANGEROUS)

rm -r subdir

# Erstellen Sie im Verzeichnis ~/devops/clbasics eine Kopie von clbasics.txt mit dem Namen clbasics-copy.txt
# Create a copy of clbasics.txt in the directory ~/devops/clbasics with the name clbasics-copy.txt

cp ~/devops/clbasics/clbasics.txt ~/devops/clbasics/clbasics-copy.txt

# Erstellen Sie einen symbolischen Link clbasics-link.txt auf die Datei clbasics.txt
# create a symbolic link clbasics-link.txt to the file clbasics.txt

ln -s ~/devops/clbasics/clbasics.txt ~/devops/clbasics/clbasics-link.txt

# Geben Sie die letzten 5 Zeilen der Log-Datei /var/log/kern.log aus
# Print the last 5 lines of the log file /var/log/kern.log

tail -n 5 /var/log/kern.log

# Verfolgen Sie evtl. neue Einträge in der /var/log/kern.log 
# Follow newly added lines in the log file /var/log/kern.log

tail -f /var/log/kern.log

# Erzeugen Sie mit dem Texteditor nano eine Textdatei nano.txt mit beliebigem Inhalt
# Create with the text editor nano a text file nano.txt with arbitrary content

nano nano.txt

## DOWNLOADING FILES 

# Laden Sie mit curl die englische Ilias als Textdatei von https://gitlab.com/rwoerzbe/public/-/raw/main/iliad.txt und speichern Sie sie in iliad.txt
# Load with curl the Iliad as text file from https://gitlab.com/rwoerzbe/public/-/raw/main/iliad.txt and save it in iliad.txt

curl -o iliad.txt https://gitlab.com/rwoerzbe/public/-/raw/main/iliad.txt

## PRINTING AND FILTERING

# Geben Sie den Inhalt von iliad.txt in einem Rutsch aus
# Output the content of iliad.txt in one go

cat iliad.txt

# Geben Sie den Inhalt von iliad.txt seitenweise aus, wobei Sie die Ausführung jederzeit abbrechen können
# Output the content of iliad.txt page by page, you can cancel the execution at any time

less iliad.txt

# Geben Sie mit grep alle Zeilen in iliad.txt aus, die das Wort "Greece" beinhalten
# Use grep to output all lines in iliad.txt that contain the word "Greece"

grep "Greece" iliad.txt

## PIPING: command1 | command2

# Zählen Sie mit grep und wc die Zeilen in iliad.txt, die das Wort "Greece" beinhalten (TIPP: Verwenden Sie Piping: command1 | command2 )
# Count with grep and wc the lines in iliad.txt which contain the word "Greece" (HINT: Use piping: command1 | command2)

grep -c "Greece" iliad.txt | wc -l

# Zählen Sie mit egrep (oder 'grep -e') die Zeilen in iliad.txt, die das Wort Greece beinhalten oder ein Wort, das mit Z beginnt.
# Count with egrep (or 'grep -e') the lines in iliad.txt that contain the word Greece or a word that starts with Z.

egrep -c "Greece|Z\w*" iliad.txt

## PROCESS SUBSTITUTION: <(command)

# Geben Sie mit grep alle Zeilen in https://gitlab.com/rwoerzbe/public/-/raw/main/iliad.txt aus, die das Wort "Greece" beinhalten, diesem Mal ohne die Datei lokal zu speichern. TIPP: Verwenden Sie Command Substitution: <(command)
# Use grep to output all lines in https://gitlab.com/rwoerzbe/public/-/raw/main/iliad.txt that contain the word "Greece". This time, you should do this without saving the text in a local file like iliad.txt. HINT: Use command substitution: <(command)

curl -s https://gitlab.com/rwoerzbe/public/-/raw/main/iliad.txt | grep "Greece"

## VARIABLE (EXPANSION): ${VARIABLE}

# Deklarieren Sie eine Variable MYFILE mit dem Wert "myfile.txt" derart, dass der Wert in nachfolgenden Kommandozeilen ausgelesen werden kann
# Declare a variable MYFILE with value "myfile.txt", such that it can be used in the following command lines.

MYFILE="myfile.txt"

# Erzeugen Sie eine Datei mit Dateinamen der in MYFILE steht. (TIPP: Verwenden Sie hierzu die Parameter-Expansion)
# Create a file with filename identical to the value of MYFILE. (HINT: Use the parameter expansion for this)

touch "$MYFILE"

## COMMAND SUBSTITUTION: $(command)

# Erzeugen Sie eine Datei mit Dateinamen identisch zur Anzahl der Wörter in einer Textdatei (beispielsweise iliad.txt). TIPP: Verwenden Sie eine Redirection und Command-Substitution
# Create a file with filename identical to the number of words in a text file (for example iliad.txt). HINT: Use redirection and command substitution

word_count=$(wc -w < iliad.txt)
touch "${word_count}.txt"

## REDIRECTIONS: command > file

# Schreiben Sie alle bislang ausgeführten Kommandozeilen in eine Datei commands.txt
# Write all command lines executed so far into a file commands.txt

history > commands.txt

# Erzeugen Sie eine Datei sources.txt mit der URL https://gitlab.com/rwoerzbe/public/-/raw/main/bibel.txt als Inhalt (eine Zeile)
# Create a file sources.txt with URL https://gitlab.com/rwoerzbe/public/-/raw/main/bibel.txt as its content (one line)

echo "https://gitlab.com/rwoerzbe/public/-/raw/main/bibel.txt" > sources.txt

# Fügen Sie die URL https://gitlab.com/rwoerzbe/public/-/raw/main/iliad.txt als zweite Zeile in sources.txt hinzu
# Add URL https://gitlab.com/rwoerzbe/public/-/raw/main/iliad.txt as second line in sources.txt

echo "https://gitlab.com/rwoerzbe/public/-/raw/main/iliad.txt" >> sources.txt

## EXTENDED ARGUMENTS: xargs

# Verwenden Sie cat, piping, xargs, curl, nochmal piping und wc, um die Gesamtzahl von Wörtern in den Remote-Dateien, die in sources.txt aufgelistet sind, auszugeben.
# Use cat, piping, xargs, curl, piping again, and wc to count the total words in the remote files that are in sources.txt

cat sources.txt | xargs -I {} curl -s {} | wc -w

## PERMISSIONS

# Sorgen Sie dafür, dass commands.txt nur von aktuellen Benutzer gelesen und geschrieben werden kann, aber nicht von seiner Gruppe oder anderen
# Make commands.txt read- and writable only for the current user but not for its group nor other users.

chmod 600 commands.txt

# Geben Sie den Inhalt des Verzeichnisses /root aus. Verwenden Sie zur Erlangung notwendiger Rechte hierfür sudo.
# Output the contents of the /root directory. Use sudo to obtain necessary permissions for this.
#Sudi command is runned in Google Cloud Shell
sudo ls -l /root

# Legen Sie im Kontext eine Gruppe devops an.
# Create a group named devops.

sudo groupadd devops

# Geben Sie alle Gruppen aus, in denen der aktuelle Benutzer Mitglied ist
# Print all the groups the current user is a member of

id -Gn

# Fügen Sie den aktuelle Benutzer der Gruppe devops hinzu
# Create a group named devops

sudo usermod -a -G devops $(whoami)

# Sorgen Sie dafür, dass Mitglieder der Gruppe devops commands.txt lesen können
# Make sure that members of the group devops can read commands.txt
sudo chown :devops commands.txt #Change the group Ownership

sudo chmod 640 commands.txt #Set file permission '6' sets Read and write permissions for the owner
                            #'4' sets read-only permission for the group.
                            #'0' denies permission for others.

## HELP

# Geben Sie die Hilfe zur Befehlssyntax des Kommandos ps aus
# Print the command syntax of the command ps

ps [options]

# Lassen Sie die Manual-Page (man page) des Befehls ps anzeigen
# Display the manual page (man page) of the command ps

man ps

## PROCESSES

# Geben Sie alle Prozesse des Systems mit jeweils den Spalten PID, TTY, TIME und CMD als Hierarchie (Tree) aus
# Print all processes of the system with columns PID, TTY, TIME and CMD respectively as a hierarchy (tree)
ps -l or
pstree -p

## ALIASES

# Definieren Sie ein Alias gitcl für das Kommando 'git log --oneline --graph --decorate --all'
# Define an alias gitcl for the command 'git log --oneline --graph --decorate --all'

git config --global alias.gitcl "log --oneline --graph --decorate --all"

## DOT-FILES

# Sorgen Sie dafür, dass das Alias bei jedem Login des aktuellen Users definiert ist durch Anpassung der ~/.bash_aliases
# Make sure the alias is defined every time the current user logs in by modifying the ~/.bash_aliases

nano ~/.bash_aliases # Creates file .bash_aliases
alias gitcl="git log --oneline --graph --decorate --all" #alias defination addedin file .bash_aliases
nano ~/.bashrc #Created file .bashrc then added the following line in file .bashrc
if [ -f ~/.bash_aliases ]; then
    . ~/.bash_aliases
fi
source ~/.bashrc #Apply changes in running terminal

# Sorgen Sie dafür, dass die ~/.bash_aliases neu ausgewertet wird (ohne Relogin)
# Make sure that the ~/.bash_aliases is re-evaluated (without relogin)

source ~/.bash_aliases


## HISTORY

# Erzeugen Sie im Kontext Ihres aktuellen Users eine Datei /root/file.txt. (Dies wird aus Rechtegründen fehlschlagen.)
# Create a file /root/file.txt with the permissions of your current user. (This will fail due to insufficient permissions.)

touch /root/file.txt

# Führen Sie den vorherigen Befehl mittels sudo aus, ohne ihn neu einzugeben
# Repeat the last command with sudo without typing it in again

sudo !!

# Führen Sie die letzten Kommandozeile aus, der mit ps angefangen hat, ohne die Kommandozeile neu eingeben zu müssen.
# Repeat the last command that began with ps without typing it in again

!!

# Geben Sie eine beliebiges Kommando ein, ohne dass dieses in der Kommandozeilen-History erscheint
# type an arbitrary command but avoid it to be put in the command history
 ps -l #Command prefixed with space will not be addded in the history

## INSTALLING

# Suchen Sie mit apt nach Paketen, in deren Beschreibung 'Rust systems programming language' vorkommt
# Search with apt for packages with 'Rust systems programming language' in their description

apt-cache search 'Rust systems programming language'

# Installieren Sie das Paket rustc
# Install the package rustc 

sudo apt install rustc

# Überprüfen Sie anschließend, ob das Executable rustc aufrufbar ist
# Then check if the rustc executable is callable

rustc --version

# Geben Sie den Pfad aus, in denen das Executable rustc liegt
# Specify the path where the rustc executable is located

which rustc