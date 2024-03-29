#!/bin/bash

###############################################################
# Kills all processes identified as ToolboxApplication by jps #
# and all processes that listen on port 8080                  #
###############################################################

# Function to get the OS type
get_ostype() {
    local ostype
    case "$(uname -s)" in
        Linux*)     ostype="linux64";;
        Darwin*)    
            case "$(uname -m)" in
            x86_64*)    ostype="mac-x64";;
            arm64*)     ostype="mac-arm64";;
            esac
        ;;
        CYGWIN*)    ostype="win64";;
        MINGW*)     ostype="win64";;
        *)          echo "unknown";;
    esac
    echo "$ostype"
}

case $(get_ostype) in
    win64*)
        taskkill //PID $(jps | grep ToolboxApplication | awk '{print $1}') //F
        taskkill //PID $(netstat -ano | findstr "0.0.0.0:8080" | awk '{print $5}') //F
        ;;
    *)
        kill $(jps | grep ToolboxApplication | awk '{print $1}')
        kill $(netstat -ano | findstr "0.0.0.0:8080" | awk '{print $5}')
        ;;
esac

