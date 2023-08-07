# Consecutive Numbers
Write a function that decides whether a given String contains a set of consecutive numbers (either ascending or descending).
Valid only for positive longs.

Examples:
isConsecutive(“12345”) -> true
`// Contains a set of consecutive ascending numbers, i.e. 1, 2, 3, 4, 5`

isConsecutive(“12131415”) -> true
`// Contains a set of consecutive ascending numbers, i.e. 12, 13, 14, 15`

isConsecutive(“122121120119118”) -> true
`// Contains a set of consecutive descending numbers, i.e. 122, 121, 120, 119, 118`

isConsecutive(“12312412516”) -> false
`// Does not contain a set of consecutive numbers, regardless of how you group the digits`

## Build and execute tests

```
./gradlew clean build
```

## Algorithm analyzer
Worst case scenario:
```
O((N/2) x (N))
```

With predefined data we can do much quicker, but it will take more memory. 

It is always a trade-off between: 
```
more memory -> faster time, 
less memory -> slower time.
```
