package us.patr0n;

/**
 * Created by patr0nus on 9/23/14.
 */
public class Program {
    public static void main(String[] args) {
        System.out.println("Testing stack");
        ImmutableStack<Integer> stack = new ImmutableStack()
                .push(5)
                .push(2)
                .push(8)
                .push(6)
                .pop()
                .push(4);
        for (Integer i : stack) {
            System.out.print(i);
        }//4825

        System.out.print("\n\n");
        System.out.println("Testing queue");
        ImmutableQueue<Integer> queue = new ImmutableQueue<Integer>()
                .enqueue(5)
                .enqueue(2)
                .enqueue(8)
                .enqueue(6)
                .dequeue()
                .enqueue(4);
        for (Integer i : queue) {
            System.out.print(i);
        }//2864

    }
}
