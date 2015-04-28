all: document/soutenance.pdf

document/soutenance.pdf: slides/soutenance.tex
	pdflatex -output-directory document/ slides/soutenance.tex
	pdflatex -output-directory document/ slides/soutenance.tex
	rm document/*.toc document/*.out document/*.aux document/*.cb document/*.cb2 document/*.log 

clean: 
	rm document/*
