import java.util.LinkedList;
import java.util.List;


public class BlockingQueue
{
   private List<Integer> queue = new LinkedList<Integer>();
   private int limit = 10;
   
   public BlockingQueue(int limit) {
      this.limit = limit;
   }
   
   public synchronized void enqueue(Integer item) throws InterruptedException {
      while(this.queue.size() == this.limit) {
         wait();
      }
      if(this.queue.size() == 0)
         notifyAll();
      this.queue.add(item);
   }
   
   public synchronized Integer dequeue() throws InterruptedException {
      while(this.queue.size() == 0) {
         wait();
      }
      if(this.queue.size() == limit)
         notifyAll();
      return this.queue.remove(0);
   }
}
