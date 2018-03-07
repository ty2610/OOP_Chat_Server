public class Tester
{
 /*
    Patrick L. Jarvis
    December 2, 2015

    Threads example
 */

 public static void main (String[] args)
 {
  PettyCash pettyCash;
  Saver     saver;
  Spender   spender;

  pettyCash = new PettyCash();
  saver     = new Saver(pettyCash);
  spender   = new Spender(pettyCash);

  new Thread(saver).start();
  new Thread(spender).start();
  new Thread(spender).start();
  new Thread(spender).start();
 }

}