#!/bin/bash

###############################################################
# Kills all processes identified as chromedriver
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
        # some Windows installation have only either tskill or taskkill so use both
        tskill chromedriver
        taskkill //IM chromedriver.exe //F
        ;;
    *)
        pkill chromedriver
        ;;
esac

# exit 0 regardless of taskkill or pkill return codes
exit 0
