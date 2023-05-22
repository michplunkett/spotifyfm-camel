#!/bin/bash

# This script uses the `sed` command to change the title-cased JSON keys into
# their respective camel-cased equivalents for all *.json files within its respective
# directory.
# This was done because the original JSON keys for the LastFM objects were created
# in title-case, and all of the SpotifyFM messages were in camelcase, but this
# created problems regarding the serialization and deserialization of JSON objects
# between the Processor steps.

# Usage
# ./replace_words.sh

sed -i  ''  's/\"Duration\"/\"duration\"/g' *.json
sed -i  ''  's/\"Artist\"/\"artist\"/g' *.json
sed -i  ''  's/\"AlbumName\"/\"albumName\"/g' *.json
sed -i  ''  's/\"ArtistUUID\"/\"artistUUID\"/g' *.json
sed -i  ''  's/\"ListenDate\"/\"listenDate\"/g' *.json
sed -i  ''  's/\"LowerCaseArtist\"/\"lowerCaseArtist\"/g' *.json
sed -i  ''  's/\"Name\"/\"name\"/g' *.json
sed -i  ''  's/\"Duration\"/\"duration\"/g' *.json
sed -i  ''  's/\"Rank\"/\"rank\"/g' *.json
sed -i  ''  's/\"SpotifyID\"/\"spotifyID\"/g' *.json
sed -i  ''  's/\"PlayCount\"/\"playCount\"/g' *.json
