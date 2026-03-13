package com.demo.playground.util.lowleveldesign.solid.liskov.bad;

public class LiskovsMain {
    public static void main(String[] args) {
        File file = new ReadOnlyFile();
        file.read();
        file.write(); // This is not supported and not expected to be handled with the exception message as given
    }
}


interface File {
    void read();
    void write();
}

class ReadOnlyFile implements File {
    @Override
    public void read() {
        System.out.println("Reading file");
    }

    @Override
    public void write() {
        throw  new UnsupportedOperationException("");
    }

}

class WritableFile implements File {
    @Override
    public void read() {
        System.out.println("Reading file");
    }

    @Override
    public void write() {
        System.out.println("Writing file");
    }
}


