/*
158. Read N Characters Given Read4 II - Call multiple times My Submissions Question
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
*/

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    private char [] buff = new char[4]; // buffer to hold the char, we may have some left over as n %4 may not equal 0
    private int offset;// indicate where the next read should start in the buffer
    private int leftSize = 0; // indicate how many leftover from privious read
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
       int readBytes = 0;
       boolean eof = false;
       
       while( !eof && readBytes < n){
           int sz = leftSize > 0 ? leftSize : read4(buff); // always read the leftover in the buffer before reading new ones
           if(leftSize == 0 && sz < 4){
               eof = true; // only if no leftOver, and last read smaller than 4, we know for sure it's eof
           }
           
           int bytes = Math.min(sz, n - readBytes);
           System.arraycopy(buff, offset, buf, readBytes,bytes);
           offset = (offset+bytes)%4; //update index in buffer
           leftSize = sz - bytes;  //update leftover in buffer, starts with sz, and consume bytes
           readBytes += bytes; // update content in buf
       }
       
       return readBytes;
    }
}