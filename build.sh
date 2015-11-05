#!/bin/bash

mkdir build
cd build
cp ../latex/* . -r

for file in *.tex
do
	eval "pdflatex $file; pdflatex $file; pdflatex $file" & 
done

wait
mkdir ../pdf
cp *.pdf ../pdf/
cd ..
rm build -r
