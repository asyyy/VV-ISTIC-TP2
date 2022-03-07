# Extending PMD

Use XPath to define a new rule for PMD to prevent complex code. The rule should detect the use of three or more nested `if` statements in Java programs so it can detect patterns like the following:

```Java
if (...) {
    ...
    if (...) {
        ...
        if (...) {
            ....
        }
    }

}
```
Notice that the nested `if`s may not be direct children of the outer `if`s. They may be written, for example, inside a `for` loop or any other statement.
Write below the XML definition of your rule.

You can find more information on extending PMD in the following link: https://pmd.github.io/latest/pmd_userdocs_extending_writing_rules_intro.html, as well as help for using `pmd-designer` [here](https://github.com/selabs-ur1/VV-TP2/blob/master/exercises/designer-help.md).

Use your rule with different projects and describe you findings below. See the [instructions](../sujet.md) for suggestions on the projects to use.

## Answer


### Xpath
Notre xpath : //IfStatement//IfStatement//IfStatement

### La règle
```xml=
<xml version="1.0"?>

<ruleset name="Custom Rules" xmlns="http://pmd.sourceforge.net/ruleset/2.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 https://pmd.sourceforge.io/ruleset_2_0_0.xsd">

   <description>
        My custom rules
    </description>


   <rule name="NotTripleIf" language="java" message="YOU CAN'T HAVE 3 IF IMBRICATED" class="net.sourceforge.pmd.lang.rule.XPathRule">
      <description>
THERE IS NOT DESCRIPTION NEEDED
 </description>
      <priority>1</priority>
      <properties>
         <property name="version" value="2.0" />
         <property name="xpath">
            <value>
               <![CDATA[
//IfStatement//IfStatement//IfStatement
]]>
            </value>
         </property>
      </properties>
   </rule>

</ruleset>%
```

### Le résultat
Le résultat de notre règle dans les projets apache-commons est cohérent avec le fonction désiré de la règle:

Prenons par exemple, ce résultat dans le fichier AbstractPatriciaTrie.java à la ligne 168 : 
![](https://i.imgur.com/QrmGWwN.png)

On constate bien une triple imbrication de if entre les lignes 155, 163 et 168 : 
![](https://i.imgur.com/sHVYUkV.png)



Notre fichier de résultat:

```
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1509:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1511:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1511:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1511:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1513:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1513:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1513:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1513:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1513:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1513:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1515:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1515:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1515:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1515:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1515:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1515:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1515:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1515:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1515:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/CollectionUtils.java:1515:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/MapUtils.java:230:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/MapUtils.java:233:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/MapUtils.java:236:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/MapUtils.java:934:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/MapUtils.java:937:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/MapUtils.java:1695:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/MapUtils.java:1698:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/MapUtils.java:1698:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/MapUtils.java:1698:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/MapUtils.java:2025:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:523:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:536:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:926:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:935:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:952:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:961:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:993:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:1016:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:1020:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:1021:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:1021:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:1021:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:1065:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:1097:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:1155:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java:1180:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/comparators/ComparatorChain.java:282:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/iterators/CollatingIterator.java:359:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/iterators/ObjectGraphIterator.java:140:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/list/CursorableLinkedList.java:511:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/AbstractReferenceMap.java:742:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/CompositeMap.java:142:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:159:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:163:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:167:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:230:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:234:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:238:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:331:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:337:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:343:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:526:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:534:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:545:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:558:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:566:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/Flat3Map.java:579:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/LRUMap.java:324:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/LRUMap.java:330:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/map/LRUMap.java:341:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/sequence/SequencesComparator.java:267:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/set/CompositeSet.java:378:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/set/CompositeSet.java:383:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/trie/AbstractPatriciaTrie.java:168:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/trie/AbstractPatriciaTrie.java:883:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
/Users/alexysguerin/fac/commons-collections/src/main/java/org/apache/commons/collections4/trie/AbstractPatriciaTrie.java:1212:	NotTripleIf:	YOU CANT HAVE 3 IF IMBRICATED
```

