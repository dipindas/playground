package com.demo.playground.util.lowleveldesign.solid.liskov.good;

/**
 * LSP:
 * objects of a superclass should be replaceable with objects of its subclasses without
 * affecting the correctness or behavior of the program
 *
 * This can be achieved by splitting a generic Interface to more specific interfaces
 */
public class LiskovsMain {

    private static void readAnyFile(ReadableFile readableFile) { //Both readable and writable objects can be substituted here.
        // So LSP is satisfied
        readableFile.read();
    }

    public static void main(String[] args) {
        WritableFile writableFile = new WritableFile();
        writableFile.read();
        writableFile.write();

        ReadableFile readableFile = new ReadOnlyFile();
        readableFile.read();
        // readableFile.write()  // This is compilation error - Not supported - safe coding

        readAnyFile(readableFile);
        readAnyFile(writableFile);
    }
}

interface Readable {
    void read();
}

interface Writable {
    void write();
}

class ReadableFile implements Readable {
    @Override
    public void read() {
        System.out.println("Reading from file");
    }
}

class ReadOnlyFile extends ReadableFile {

}

class WritableFile extends ReadableFile implements Writable {
    @Override
    public void write() {
        System.out.println("Writing to a file");
    }
}
