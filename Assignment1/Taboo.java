
/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.util.*;

public class Taboo<T> {
	private final Map<T, Set<T>> mappedRules;
	
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		mappedRules = new HashMap<>();

		for (int i = 0; i < rules.size() - 1; i++) {
			T i_th = rules.get(i);
			T i_th_next = rules.get(i + 1);

			if (i_th != null && i_th_next != null) {
				if (mappedRules.containsKey(i_th)) {
					Set<T> new_set = mappedRules.get(i_th);
					new_set.add(i_th_next);
				} else {
					Set<T> s = new HashSet<>();
					s.add(i_th_next);
					mappedRules.put(i_th, s);
				}
			}
		}
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		if (mappedRules.containsKey(elem)) {
			return mappedRules.get(elem);
		}
		return Collections.emptySet();
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			Set<T> s = mappedRules.get(list.get(i));
			if (s != null && s.contains(list.get(i + 1))) {
				list.remove(i + 1);
				i--;
			}
		}
	}
}
