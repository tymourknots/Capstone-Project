# FINAL CAPSTONE PROJECT

My Capstone Project is a Java-based application designed to analyze text files and identify the most frequently occurring words. The program reads a specified text file, counts the occurrences of each word, and displays the results in a sorted manner, primarily by frequency (from highest to lowest) and secondarily by alphabetical order. It supports multiple data structures, including Binary Search Tree (BST), AVL Tree, and HashMap, to store word counts, allowing for performance comparison between different implementations. The application also includes JUnit tests to validate its functionality and ensure reliability.

#### 1. What lessons did you learn from working on this project?

I learned how different data structures significantly impact the performance of various operations such as insertion, lookup, and deletion. While AVL trees offer better lookup times due to self-balancing, they incur extra overhead when rebalancing during frequent insertions. This project demonstrated how these trade-offs play out in real-world scenarios. Additionally, I gained hands-on experience in measuring and analyzing performance metrics to validate theoretical expectations using the time command in the Linux terminal.

#### 2. What were some of the specific challenges you faced, and how did you overcome them?

One of the primary challenges was ensuring accurate text parsing, especially when dealing with edge cases such as words starting with double hyphens, special characters, and URL patterns like "www". I solved these issues by refining my regular expressions and incorporating logic to either ignore or count these edge cases. Another challenge was setting up JUnit testing within a Maven project structure, as I encountered issues with Maven dependencies and directory structure. I resolved this by correctly configuring the pom.xml file and ensuring the source files were in their expected locations.

#### 3. What insights did you gain regarding performance optimization and testing?

I gained insight into how self-balancing structures like AVL trees may not always be the best option when the operation involves frequent insertions. While AVL trees generally outperform BSTs in lookup operations, the cost of rebalancing during insertions resulted in slower performance for this project. The HashMap data structure, on the other hand, showed the best overall performance, reaffirming the importance of choosing the right data structure based on the specific needs of a program.

## Skills Acquired:

I acquired and strengthened skills in several areas:

#### 1. Java Programming: 

I reinforced my understanding of core Java concepts such as file handling, string manipulation, and working with collections.

#### 2. Data Structures & Algorithms: 

I gained practical experience in implementing and using BSTMap, AVLTreeMap, and MyHashMap, and analyzing the trade-offs between these structures.

#### 3. Unit Testing: 

This project enhanced my proficiency with JUnit testing, allowing me to create comprehensive test cases that validate program behavior under different scenarios.

#### 4. Maven Integration: 

I learned how to set up Maven projects, configure dependencies, and troubleshoot build errors, which streamlined the build and testing processes.

#### 5. Performance Analysis: 

I learned how to measure runtime performance using the command line and used the results to make informed decisions on which data structure was optimal for the problem at hand.


## Performance Results:

**bst:** 2.212 seconds

**avl:** 2.244 seconds

**hash:** 2.119 seconds

### Explanation of Results

#### 1. Hash Map (hash):

The hash map was the fastest because it provides constant time complexity, O(1), for both insertions and lookups, minimizing the time spent on these operations.

#### 2. Binary Search Tree (bst):

The BST was slightly slower than the hash map, which makes sense due to its logarithmic time complexity, O(log n). It’s surprising that it wasn’t significantly slower, which could indicate that the tree was fairly balanced for the given input.

#### 3. AVL Tree (avl):

The AVL tree’s performance was very close to the BST’s, but slightly slower due to the additional rebalancing operations that ensure the tree remains balanced. This additional overhead likely accounts for the marginally longer runtime.