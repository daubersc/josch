#!/bin/zsh

HOST="http://127.0.0.1"
PORT=8833
URL="compare"

DIR=$(dirname "$0")

eq=$(curl --silent -H "Content-Type: application/json" --data @$DIR/schemas/s1s1.json $HOST:$PORT/$URL)
[[ $eq == "equivalent" ]] && echo "Equivalence passed" || (echo "Error for equivalence" && exit 1)

sub=$(curl --silent -H "Content-Type: application/json" --data @$DIR/schemas/s2s1.json $HOST:$PORT/$URL)
[[ $sub == "subset" ]] && echo "Subset passed" || (echo "Error for subset" && exit 1)

sup=$(curl --silent -H "Content-Type: application/json" --data @$DIR/schemas/s1s2.json $HOST:$PORT/$URL)
[[ $sup == "superset" ]] && echo "Superset passed" || (echo "Error for superset" && exit 1)

in=$(curl --silent -H "Content-Type: application/json" --data @$DIR/schemas/s2s3.json $HOST:$PORT/$URL)
[[ $in == "incomparable" ]] && echo "Incomparable passed" || (echo "Error for incomparable" && exit 1)

error=$(curl --silent -H "Content-Type: application/json" --data @$DIR/schemas/s1.json $HOST:$PORT/$URL)
echo "################################################################"
echo "Error expected:"
echo $error
echo "################################################################"