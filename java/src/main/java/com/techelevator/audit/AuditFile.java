package com.techelevator.audit;

import java.io.*;

public class AuditFile implements Closeable {
    private File auditFile;
    private PrintWriter writer;

    public AuditFile(String pathName) {
        this.auditFile = new File(pathName);
        if (this.auditFile.exists()){
            try{
                this.writer = new PrintWriter(new FileWriter(this.auditFile, true));
            } catch (IOException e) {
                System.out.println("File not able to be written");
            }
        }
        else {
            try{
                this.writer = new PrintWriter(this.auditFile);
            } catch (FileNotFoundException e) {
                System.out.println("File not able to be written to");
            }
        }
    }

    public void write(String message) {
        this.writer.println(message);
        this.writer.flush();
    }

    @Override
    public void close() {
        this.writer.close();
    }
}
