all: outputs/irBureauPartagé.pdf

outputs/irBureauPartagé.pdf: report/main.tex 
	pdflatex -output-directory outputs/ report/main.tex 
	pdflatex -output-directory outputs/ report/main.tex 
	rm outputs/*.log
	rm outputs/*.out
	rm outputs/*.aux
	mv outputs/main.pdf outputs/irBureauPartagé.pdf

clean:
	rm -rf outputs/*