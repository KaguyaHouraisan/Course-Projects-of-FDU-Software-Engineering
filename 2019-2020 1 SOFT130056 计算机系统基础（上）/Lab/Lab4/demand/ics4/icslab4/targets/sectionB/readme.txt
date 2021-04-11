normal output:
>./ls dfdafsf/ggdnfdjfa
./ls: cannot access the file dfdafsf/ggdnfdjfa: No such file or directory


crash output:
>./ls test/abug
*** buffer overflow detected ***: ./ls terminated
======= Backtrace: =========
[0x425c61]
[0x46cc32]
[0x46c9de]
[0x46c4c2]
[0x4068f6]
[0x401321]
[0x411a06]
[0x411ff5]
[0x402919]
======= Memory map: ========
00400000-004ff000 r-xp 00000000 08:01 15514971                           /home/limin/Desktop/ics_fuzzer/bins/bin/ls
006fe000-00701000 rw-p 000fe000 08:01 15514971                           /home/limin/Desktop/ics_fuzzer/bins/bin/ls
00701000-00704000 rw-p 00000000 00:00 0 
014ee000-01511000 rw-p 00000000 00:00 0                                  [heap]
7f3ed24c0000-7f3ed24c1000 rw-p 00000000 00:00 0 
7f3ed24c1000-7f3ed294c000 r--p 00000000 08:01 12714122                   /usr/lib/locale/locale-archive
7ffd03995000-7ffd039b7000 rw-p 00000000 00:00 0                          [stack]
7ffd039b9000-7ffd039bc000 r--p 00000000 00:00 0                          [vvar]
7ffd039bc000-7ffd039be000 r-xp 00000000 00:00 0                          [vdso]
ffffffffff600000-ffffffffff601000 r-xp 00000000 00:00 0                  [vsyscall]
[1]    26710 abort (core dumped)  ./ls test/abug

