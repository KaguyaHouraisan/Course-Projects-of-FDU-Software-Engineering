## Lab 1测试用例 ##

### --用例 1 ### 

##### Input #####

```
A "world"
a "hello" 
d 3
D 5
u
u
r
l 3
D 5
D 20
```

##### Output #####

```
"world"

"helloworld"

"loworld"

"lo"

"loworld"

"helloworld"

"loworld"

1 d 3
2 a "hello"
3 A "world"

"lo"

""
```

### --用例 2 ###

##### Input #####

```
d 3
a "the second "
A "test"
u
m 2 m2
$m2
d 6
u
u
r
l 3
```

##### Output #####

```
""

"the second "

"the second test"

"the second "

"the second  second "

"cond  second "

"the second  second "

"the second "

"the second  second "

1 m2
2 a "the second "
3 d 3
```

### --用例 3 ###

##### Input #####

```
a "Object-Oriented Design and Analysis"
A "2020"
D 10
d 3
s
u
u
r
D 6
l 3
```

##### Output #####

```
"Object-Oriented Design and Analysis"

"Object-Oriented Design and Analysis2020"

"Object-Oriented Design and An"

"ect-Oriented Design and An"

"ect-Oriented Design and An"

"Object-Oriented Design and An"

"Object-Oriented Design and Analysis2020"

"Object-Oriented Design and An"

"Object-Oriented Design "

1 D 6
2 D 10
3 A "2020"
```

### --用例 4 ###

##### Input #####

```
a "Unified modeling language"
d 21
A "Design patterns"
m 2 m1
$m1
l 2
u
u
u
r
r
```

##### Output #####

```
"Unified modeling language"

"uage"

"uageDesign patterns"

"Design patterns"

1 m1
2 A "Design patterns"

"uageDesign patterns"

"uage"

"Unified modeling language"

"uage"

"uageDesign patterns"
```

注：

1、以上列出的output内容中的双引号只是便于同学们理解，实际编写程序时控制台输出的文本无需加双引号。

2、undo 取消的动作被认为没有执行过，除非它redo重新执行了。

## 分数分配说明 ##

1、本测试用例共4个，每个20分，共计80分

2、每个测试用例都分别有10条命令，所以一条命令输出正确的话即可得2分

3、最终将分数累计得出总得分

