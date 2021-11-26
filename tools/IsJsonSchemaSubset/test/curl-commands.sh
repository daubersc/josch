#!/bin/zsh

HOST="http://127.0.0.1"
PORT=8834
URL="compare"

DIR=$(dirname "$0")

eq=$(curl --silent -H "Content-Type: application/json" --data @$DIR/schemas/s1s1.json $HOST:$PORT/$URL)
echo $eq

sub=$(curl --silent -H "Content-Type: application/json" --data @$DIR/schemas/s2s1.json $HOST:$PORT/$URL)
echo $sub

sup=$(curl --silent -H "Content-Type: application/json" --data @$DIR/schemas/s1s2.json $HOST:$PORT/$URL)
echo $sup

in=$(curl --silent -H "Content-Type: application/json" --data @$DIR/schemas/s2s3.json $HOST:$PORT/$URL)
echo $in

error=$(curl --silent -H "Content-Type: application/json" --data @$DIR/schemas/s1.json $HOST:$PORT/$URL)
echo "################################################################"
echo "Error expected:"
echo $error
echo "################################################################"