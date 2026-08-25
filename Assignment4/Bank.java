// Bank.java

/*
 Creates a bunch of accounts and uses threads
 to post transactions to the accounts concurrently.
*/

import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Bank {
	private BlockingQueue<Transaction> b_queue;
	private Account[] accs;
	private CountDownLatch cdl;

	private final Transaction nullTrans = new Transaction(-1, 0, 0);
	public static final int ACCOUNTS = 20;	 // number of accounts

	/*
	 Reads transaction data (from/to/amt) from a file for processing.
	 (provided code)
	 */
	public void readFile(String file) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));

				// Use stream tokenizer to get successive words from file
				StreamTokenizer tokenizer = new StreamTokenizer(reader);

				while (true) {
					int read = tokenizer.nextToken();
					if (read == StreamTokenizer.TT_EOF) break;  // detect EOF
					int from = (int)tokenizer.nval;

					tokenizer.nextToken();
					int to = (int)tokenizer.nval;

					tokenizer.nextToken();
					int amount = (int)tokenizer.nval;

					Transaction t = new Transaction(from, to, amount);
					b_queue.put(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
	}

	/*
	 Processes one file of transaction data
	 -fork off workers
	 -read file into the buffer
	 -wait for the workers to finish
	*/
	public void processFile(String file, int numWorkers) {
		accs = new Account[ACCOUNTS];
		for (int i = 0; i < ACCOUNTS; i++) {
			accs[i] = new Account(this, i, 1000);
		}

		b_queue = new ArrayBlockingQueue<>(200);
		cdl = new CountDownLatch(numWorkers);

		for (int i = 0; i < numWorkers; i++) {
			Worker worker = new Worker();
			worker.start();
		}
		readFile(file);

		for (int i = 0; i < numWorkers; i++) {
			try{
				b_queue.put(nullTrans);
			} catch (InterruptedException ignored) {}
		}

		try {
			cdl.await();
		} catch (InterruptedException ignored) {}

		for (int i = 0; i < accs.length; i++) {
			System.out.println(accs[i].toString());
		}
	}

	private class Worker extends Thread {
		@Override
		public void run() {
			try {
				while(true) {
					Transaction t = b_queue.take();
					if (t.from == -1) break;

					accs[t.to].add(t.amount);
					accs[t.from].add(-t.amount);
				}
			} catch (InterruptedException ignored) {}

			cdl.countDown();
		}
	}

	/*
	 Looks at commandline args and calls Bank processing.
	*/
	public static void main(String[] args) {
		// deal with command-lines args
		if (args.length == 0) {
			System.out.println("Args: transaction-file [num-workers [limit]]");
			return;
		}
		
		String file = args[0];
		
		int numWorkers = 1;
		if (args.length >= 2) {
			numWorkers = Integer.parseInt(args[1]);
		}
		
		Bank bank = new Bank();
		bank.processFile(file, numWorkers);
	}

	public Account[] getAccs() {
		return accs;
	}
}

