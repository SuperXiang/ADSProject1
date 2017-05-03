############################################################
#
# Title: makefile for hashtagcounter project
# Author: Yingfei Xiang
# Date: 11/17/2016
#
############################################################

# Set the file name of the executable jar package
JAR_PKG = hashtagcounter.jar

# Set the entry point of the java project
ENTRY_POINT = hashtagcounter.main

# Set the RES_DIR whether the project includes res directory
RES_DIR = no

# Set the source files of the project
SOURCE_FILES = \
hashtagcounter/maxFibNode.java \
hashtagcounter/maxFibHeap.java \
hashtagcounter/main.java

# Set the java compiler
JAVAC = javac

# Set the encoding option
JFLAGS = -encoding UTF-8 

# Usage:
# Put makefile in the root directory with jar package, bin and src as following
# ├── hashtagcounter.jar
# ├── bin
# │     └── hashtagcounter
# │             ├── maxFibNode.class
# │             ├── maxFibHeap.class
# │             └── main.class
# ├── makefile
# └── src
#        └── hashtagcounter
#                ├── maxFibNode.java
#                ├── maxFibHeap.java
#                └── main.java
#
# make new: new project, create src, bin (or res) directories
# make build: compile and build project, build classes under bin
# make clean: clear classes generated
# make rebuild: rebuild project
# make run: run the project after "make build"
# make jar: package the project into an executable jar

############################################################

vpath %.class bin
vpath %.java src

# Show help message by default
Default:
	@echo "make new: new project, create src, bin (or res) directories."
	@echo "make build: build project."
	@echo "make clear: clear classes generated."
	@echo "make rebuild: rebuild project."
	@echo "make run inputfile=A outputfile=B: run the project, replace A with loc of input file, replace B with loc of output file."
	@echo "make jar: package the project into an executable jar."

build: $(SOURCE_FILES:.java=.class)

%.class: %.java
	$(JAVAC) -cp bin -d bin $(JFLAGS) $<

rebuild: clear build

.PHONY: new clear run jar

new:
ifeq ($(RES_DIR),yes)
	mkdir src bin res
else
	mkdir src bin
endif

clear:
	rm -frv bin/*

# When using make run inputfile=A outputfile=B in console, replace A with the location of input data file, and replace B with the location of output data file
run:
	java -cp bin $(ENTRY_POINT) ${inputfile} ${outputfile}

jar:
#ifeq ($(RES_DIR),yes)
#	jar cvfe $(JAR_PKG) $(ENTRY_POINT) -C bin . res
#else
#	jar cvfe $(JAR_PKG) $(ENTRY_POINT) -C bin .
#endif
	jar cvf $(JAR_PKG) -C bin/ .
