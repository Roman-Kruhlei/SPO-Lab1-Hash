package spo;

import java.util.*;

/**
 * hash:
 * Сумма кодов первой и последней букв
 */
public class Main {
	private static Hashtable<Integer, Object> hashtable = new Hashtable<>();
	private static LinkedList<String> temp = new LinkedList<>();
	private static int comparisons = 0;
	private static int collisions = 0;

	static class Table {

		void add(String string) {
			if (check(hash(string))) {
				collision(hash(string), string);
				collisions++;
			} else {
				hashtable.put(hash(string), string);
			}
		}

		/**
		 * GET method
		 * <p>
		 * if we have list in hash box -> find in list
		 */
		String getValue(String string) {
			try {
				if (hashtable.get(hash(string))
							 .getClass()
							 .equals(LinkedList.class)) {
					LinkedList<String> list = (LinkedList<String>) hashtable.get(hash(string));
					for (String s : list) {
						comparisons++;
						if (s.equals(string)) {
							return s;
						}
					}
				} else
					return (String) hashtable.get(hash(string));

			} catch (NullPointerException n) {
//               System.out.println("You don't have element with code: "+hash(string)+"\n" +
//                "Exception: "+n);
			}
			return null;
		}

		/**
		 * HASH function
		 */
		int hash(String input) {
			int first = String.valueOf(input.charAt(0))
							  .hashCode();
			int last = String.valueOf(input.charAt(input.length() - 1))
							 .hashCode();
			return first + last;
		}

		/**
		 * Collision
		 * <p>
		 * if we dont have linkedlist in this block -> create list, add prev value and new input string
		 * //
		 * if we have linkedlist -> get it from hash block, add new input string, put back list to hash block
		 */

		void collision(int hash, String string) {

			if (!hashtable.get(hash)
						  .getClass()
						  .equals(LinkedList.class)) {
				LinkedList<String> list = new LinkedList<>();
				list.add(String.valueOf(hashtable.get(hash)));
				list.add(string);
				hashtable.put(hash, list);
//              System.out.println(hashtable.get(hash).getClass());
			} else {
				LinkedList<String> list = (LinkedList<String>) hashtable.get(hash);
				list.add(string);
				hashtable.put(hash, list);
			}
		}

		/**
		 * check if we have a collision
		 */

		boolean check(int hash) {
			Set<Integer> keySet = hashtable.keySet();
			for (Integer integer : keySet) {
				if (hash == integer)
					return true;
			}
			return false;
		}

		void menu() {
			Scanner scanner = new Scanner(System.in);
			String in;
			while (true) {
				System.out.println("\nEnter string, u want to find: ");
				in = scanner.next();
				System.out.println("Find anything " + getValue(in));
				System.out.println("Number of comparisons : " + comparisons);
				comparisons = 0;
				System.out.println("\ncontinue? enter y: ");
				in = scanner.next();
				if (!in.equals("y"))
					break;
			}
		}

	}

	public static void main(String[] args) {
		Table t = new Table();
		FileReadWrite fileReadWrite = new FileReadWrite();
		fileReadWrite.writeFile();
		temp = fileReadWrite.readFile();
		Iterator<String> iterator = temp.iterator();
		while (iterator.hasNext()) {
			t.add(iterator.next());
		}
		temp.clear();
		t.menu();
		System.out.println("Number of collisions: " + collisions);
	}


}

