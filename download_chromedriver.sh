#!/bin/bash

prefix="https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/"
#version="116.0.5845.96"
version="115.0.5763.0"

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
    echo "${ostype}"
}

ostype=$(get_ostype)
url_chrome="$prefix$version/$ostype/chrome-$ostype.zip"
url_chromedriver="$prefix$version/$ostype/chromedriver-$ostype.zip"

echo "${url_chrome}"
echo "${url_chromedriver}"

mkdir -p ./target/chromedriver
temp_file=./target/chromedriver_archive
curl -sSLk "${url_chromedriver}" -o "$temp_file" && unzip "$temp_file" -d ./target/chromedriver
mv ./target/chromedriver/chromedriver-"${ostype}"/* ./target/chromedriver
rm -r ./target/chromedriver/chromedriver-"${ostype}"
rm -f "${temp_file}"

mkdir -p ./target/chrome
temp_file=./target/chrome_archive
curl -sSLk "${url_chrome}" -o "$temp_file" && unzip "$temp_file" -d ./target/chrome
mv ./target/chrome/chrome-"${ostype}"/* ./target/chrome
rm -r ./target/chrome/chrome-"${ostype}"
rm -f "${temp_file}"
