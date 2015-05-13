#include <syscall.h>


void writeint(int num) {
  char buf[20];
  char result[20] = "0\n";
  char *pos = buf;
  char *writeptr = result;
  int numWritten;
 
  // Handle negative numbers
  if (num < 0) {
    *writeptr++ = '-';
    num = -num;
  }
  
  if (num > 0) {
      
    // Build the number in reverse order
    while (num > 0) {
      *pos++ = (num % 10) + '0';
      num /= 10;
    }
    pos--;
    
    // Now we need to copy the results into the output buffer, reversed
    while (pos > buf) {
      *writeptr++ = *pos--;
    }
    *writeptr++ = *pos;
    *writeptr++ = 10;
    *writeptr++ = 0;
  } else {
    // number is 0; use default result
    writeptr = result + 3;
  }
  
  write(1, result, (writeptr - result) - 1);
  
}

// reads a character from stdin and returns it (<in> is the predefined Oodle Reader object)
int readint() {
  char buffer [256];
  int num = 0;

  read(0, &buffer, 256);
  num = atoi(buffer);

  return num;
}

// writeint() and readint() c driver test program
/*
int main(){
    
    int n = readint();
    writeint(n);
    
    return 0;
}
*/