# Using PMD

Pick a Java project from Github (see the [instructions](../sujet.md) for suggestions). Run PMD on its source code using any ruleset. Describe below an issue found by PMD that you think should be solved (true positive) and include below the changes you would add to the source code. Describe below an issue found by PMD that is not worth solving (false negative). Explain why you would not solve this issue.

## Answer

#### False positive  
L'erreur : 
```
MultiMapUtils.java:114:	ReturnEmptyCollectionRatherThanNull: Return an empty collection rather than null.
```

Le code associé : 

```java=
public static <K, V> Collection<V> getCollection(final MultiValuedMap<K, V> map, final K key) {
    if (map != null) {
        return map.get(key);
    }
    return null;
}
```

La fonction retourne null si l'input est null. 

On suppose donc c'est une volonté des développeurs pour éviter de retourner un tableau vide dans le cas où l'input est null. C'est donc selon nous un faux positif car c'est une volonté des développeurs.

#### True positive

L'erreur : 

```
SimplifyBooleanReturns:	Avoid unnecessary if..then..else statements when returning booleans
```

Le code associé : 

```java=
private boolean checkBounds() {
        if (pos - offset + 1 > max) {
            return false;
        }
        return true;
    }
```
Le code pourrait être simplifié pour éviter l'utilisation d'un if, le code modifié ressemblerait à cela : 

```java=
private boolean checkBounds() {
        return !(pos - offset + 1 > max);
    }
```
