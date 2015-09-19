import java.io.*;

class ReadThread extends Thread{

  InputStream pi = null;
  OutputStream po = null;    

  ReadThread( InputStream pi, OutputStream po) {
    this.pi = pi;
    this.po = po;
  }

  public void run() {
    int ch;
    int i;
    try { 
        for(;;) {
            i = pi.read();
            if (i == -1) { return; }
            po.write(i);
        }
    } catch (Exception e) {  }
  }

}

class SystemStream {

 public static void main( String [] args) {
    try {
      int ch;
      while (true) {
        PipedInputStream  writeIn = new PipedInputStream();
        PipedOutputStream readOut = new PipedOutputStream( writeIn );

        ReadThread rt = new ReadThread( System.in, readOut );
        ReadThread wt = new ReadThread( writeIn, System.out );

        rt.start();
        wt.start();
      }
    } catch (Exception e) {
    	System.out.println(e);
    }
  }
}
