(*^

::[	Information =

	"This is a Mathematica Notebook file.  It contains ASCII text, and can be
	transferred by email, ftp, or other text-file transfer utility.  It should
	be read or edited using a copy of Mathematica or MathReader.  If you 
	received this as email, use your mail application or copy/paste to save 
	everything from the line containing (*^ down to the line containing ^*)
	into a plain text file.  On some systems you may have to give the file a 
	name ending with ".ma" to allow Mathematica to recognize it as a Notebook.
	The line below identifies what version of Mathematica created this file,
	but it can be opened using any other version as well.";

	FrontEndVersion = "X Window System Mathematica Notebook Front End Version 2.2";

	X11StandardFontEncoding; 
	
	fontset = title, inactive, noPageBreakBelow, noPageBreakInGroup, nohscroll, preserveAspect, groupLikeTitle, center, M7, bold, e8,  24, fontName, "times";
	fontset = subtitle, inactive, noPageBreakBelow, noPageBreakInGroup, nohscroll, preserveAspect, groupLikeTitle, center, M7, bold, e6,  18, fontName, "times";
	fontset = subsubtitle, inactive, noPageBreakBelow, noPageBreakInGroup, nohscroll, preserveAspect, groupLikeTitle, center, M7, italic, e6,  14, fontName, "times";
	fontset = section, inactive, noPageBreakBelow, nohscroll, preserveAspect, groupLikeSection, grayBox, M22, bold, a20,  18, fontName, "times";
	fontset = subsection, inactive, noPageBreakBelow, nohscroll, preserveAspect, groupLikeSection, blackBox, M19, bold, a15,  14, fontName, "times";
	fontset = subsubsection, inactive, noPageBreakBelow, nohscroll, preserveAspect, groupLikeSection, whiteBox, M18, bold, a12,  12, fontName, "times";
	fontset = text, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7,  12, fontName, "times";
	fontset = smalltext, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7,  10, fontName, "times";
	fontset = input, noPageBreakInGroup, nowordwrap, preserveAspect, groupLikeInput, M42, N23, bold,  12, fontName, "courier";
	fontset = output, output, inactive, noPageBreakInGroup, nowordwrap, preserveAspect, groupLikeOutput, M42, N23, L-5,  12, fontName, "courier";
	fontset = message, inactive, noPageBreakInGroup, nowordwrap, preserveAspect, groupLikeOutput, M42, N23,  12, fontName, "courier";
	fontset = print, inactive, noPageBreakInGroup, nowordwrap, preserveAspect, groupLikeOutput, M42, N23,  12, fontName, "courier";
	fontset = info, inactive, noPageBreakInGroup, nowordwrap, preserveAspect, groupLikeOutput, M42, N23,  12, fontName, "courier";
	fontset = postscript, PostScript, formatAsPostScript, output, inactive, noPageBreakInGroup, nowordwrap, preserveAspect, groupLikeGraphics, M7, l34, w282, h287,  12, fontName, "courier";
	fontset = name, inactive, noPageBreakInGroup, nohscroll, preserveAspect, M7, italic, B65535,  10, fontName, "times";
	fontset = header, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7, italic,  12, fontName, "times";
	fontset = leftheader,  12, fontName, "times";
	fontset = footer, inactive, nohscroll, noKeepOnOnePage, preserveAspect, center, M7, italic,  12, fontName, "times";
	fontset = leftfooter,  12, fontName, "times";
	fontset = help, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7,  12, fontName, "times";
	fontset = clipboard, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7,  12, fontName, "times";
	fontset = completions, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7,  12, fontName, "courier";
	fontset = special1, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7,  12, fontName, "times";
	fontset = special2, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7,  12, fontName, "times";
	fontset = special3, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7,  12, fontName, "times";
	fontset = special4, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7,  12, fontName, "times";
	fontset = special5, inactive, nohscroll, noKeepOnOnePage, preserveAspect, M7,  12, fontName, "times";paletteColors = 128; automaticGrouping; currentKernel; 
]
:[font = title; inactive; preserveAspect; startGroup]
A Sampling of Mathematica
  
:[font = text; inactive; preserveAspect]
This file is loosely based on the "Tour of Mathematica"  in Mathematica: A System for Doing Mathematics by Computer, Second Edition, by Stephen Wolfram.  This book was published by Addison-Wesley in 1991, and is available at most bookstores.
:[font = section; inactive; preserveAspect; startGroup]
Numerical Calculations
:[font = text; inactive; preserveAspect]
You can use Mathematica as an enhanced scientific calculator.  Let's start with a simple example. 
:[font = input; preserveAspect; startGroup]
45 + 78
:[font = output; output; inactive; preserveAspect; endGroup]
123























































































;[o]
123
:[font = text; inactive; preserveAspect]
The first line here is what you type into Mathematica.  The second line is the result Mathematica gives. If you are reading this notebook on a color system, the input and output are blue, with the input in boldface.
:[font = text; inactive; preserveAspect]
Now let's try something more difficult.
:[font = input; preserveAspect; startGroup]
3^100
:[font = output; output; inactive; preserveAspect; endGroup]
515377520732011331036461129765621272702107522001























































































;[o]
515377520732011331036461129765621272702107522001
:[font = text; inactive; preserveAspect]
Unlike a calculator, Mathematica  gives an exact answer for 3 raised to the power 100.
:[font = text; inactive; preserveAspect]
Now let's really test out Mathematica.  Here is 3 raised to the power 1000.
:[font = input; preserveAspect; startGroup]
3^1000
:[font = output; output; inactive; preserveAspect; endGroup]
13220708194808066368904552597521443659654220327521481676649\
 2036822682859734670489954077831385060806196390977769687258\
 2355950954582100618911865342725257953674027620225198320803\
 8780147742289648412743904001175886180411289478156230944380\
 6156617305408667449050617812548034440554705439703889581746\
 5368254916136220830268563778582290228416398307887896918556\
 4040848989376093732421718463599386955167650189405881090604\
 2608967143886410281435038564874716583201061436613217310276\
 8902855220001























































































;[o]
13220708194808066368904552597521443659654220327521481676649\
 
 2036822682859734670489954077831385060806196390977769687258\
 
 2355950954582100618911865342725257953674027620225198320803\
 
 8780147742289648412743904001175886180411289478156230944380\
 
 6156617305408667449050617812548034440554705439703889581746\
 
 5368254916136220830268563778582290228416398307887896918556\
 
 4040848989376093732421718463599386955167650189405881090604\
 
 2608967143886410281435038564874716583201061436613217310276\
 
 8902855220001
:[font = text; inactive; preserveAspect]
This took about half a second on a 386-based MS-DOS machine.
:[font = text; inactive; preserveAspect]
Here's the result in the form you might get on a calculator.
:[font = input; preserveAspect; startGroup]
N[%]
:[font = output; output; inactive; preserveAspect; endGroup]
"1.32207"*10^"477"























































































;[o]
          477
1.32207 10
:[font = text; inactive; preserveAspect]
Here is the value of pi to two hundred decimal places.
:[font = input; preserveAspect; startGroup]
N[Pi, 200]
:[font = output; output; inactive; preserveAspect; endGroup]
3.141592653589793238462643383279502884197169399375105820974\
 9445923078164062862089986280348253421170679821480865132823\
 0664709384460955058223172535940812848111745028410270193852\
 1105559644622948954930382























































































;[o]
3.141592653589793238462643383279502884197169399375105820974\
 
 9445923078164062862089986280348253421170679821480865132823\
 
 0664709384460955058223172535940812848111745028410270193852\
 
 1105559644622948954930382
:[font = text; inactive; preserveAspect]
Mathematica knows about a big collection of mathematical functions -- most of those you would find in any book of mathematical tables.
:[font = input; preserveAspect; startGroup]
BesselJ[5, 34.6]
:[font = output; output; inactive; preserveAspect; endGroup]
0.0511826























































































;[o]
0.0511826
:[font = input; preserveAspect; startGroup]

Log[4.5 + 2I] 
:[font = output; output; inactive; preserveAspect; endGroup]
1.59421 + 0.418224*I























































































;[o]
1.59421 + 0.418224 I
:[font = input; preserveAspect; startGroup]

Zeta[1/2 + 14.3 I]
:[font = output; output; inactive; preserveAspect; endGroup]
-0.0119878 + 0.132231*I























































































;[o]
-0.0119878 + 0.132231 I
:[font = text; inactive; preserveAspect]

Mathematica can do many kinds of exact computations with integers.  FactorInteger gives the factors of an integer.
:[font = input; preserveAspect; startGroup]
FactorInteger[70612139395722186]
:[font = output; output; inactive; preserveAspect; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
{{2, 1}, {3, 2}, {43, 5}, {26684839, 1}}
:[font = text; inactive; preserveAspect]
You can also do all the standard numerical matrix computations with Mathematica.  This computes the inverse of a 2 by 2 matrix.
:[font = input; preserveAspect; startGroup]
Inverse[{{3.5, 7.2}, {-2.4, 6.4}}]
:[font = output; output; inactive; preserveAspect; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
{{0.16129, -0.181452}, {0.0604839, 0.0882056}}
:[font = text; inactive; preserveAspect]
This gives a numerical approximation to the eigenvalues of the matrix computed in the last example. 
:[font = input; preserveAspect; startGroup]
Eigenvalues[%]
:[font = output; output; inactive; preserveAspect; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
{0.124748 + 0.0981812 I, 0.124748 - 0.0981812 I}
:[font = text; inactive; preserveAspect]
Mathematica will also solve  linear programming problems. This returns a list containing the maximum value of the "objective function" and the point at which it is attained.
:[font = input; preserveAspect; startGroup]
ConstrainedMax[17 x -20 y + 18 z,
  {x - y + z < 10, x < 5, x + z > 20}, {x, y, z}]
:[font = output; output; inactive; preserveAspect; endGroup; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
{160, {x -> 0, y -> 10, z -> 20}}
:[font = section; inactive; preserveAspect; startGroup]
Algebraic Calculations
:[font = text; inactive; preserveAspect]
One of the most important features of Mathematica is its ability to deal with mathematical formulas in algebraic form.
:[font = input; preserveAspect; startGroup]
(1 + x)^3
:[font = output; output; inactive; preserveAspect; endGroup]
(1 + x)^3























































































;[o]
       3
(1 + x)
:[font = text; inactive; preserveAspect]
This is what Mathematica does if you type in a simple algebraic expression.
You can expand the expression like this:
:[font = input; preserveAspect; startGroup]
Expand[%]
:[font = output; output; inactive; preserveAspect; endGroup]
1 + 3*x + 3*x^2 + x^3























































































;[o]
             2    3
1 + 3 x + 3 x  + x
:[font = text; inactive; preserveAspect]
Mathematica gives an explicit formula for the result.
You can factor this result to get back to what you started from.
% always stands for the last result that Mathematica gave you.
:[font = input; preserveAspect; startGroup]
Factor[%]
:[font = output; output; inactive; preserveAspect; endGroup]
(1 + x)^3























































































;[o]
       3
(1 + x)
:[font = text; inactive; preserveAspect]
Now let's try a more complicated example.
:[font = input; preserveAspect; startGroup]
(1 + 2x + 5y)^7
:[font = output; output; inactive; preserveAspect; endGroup]
(1 + 2*x + 5*y)^7























































































;[o]
               7
(1 + 2 x + 5 y)
:[font = input; preserveAspect; startGroup]
Expand[%]
:[font = output; output; inactive; preserveAspect; endGroup]
1 + 14*x + 84*x^2 + 280*x^3 + 560*x^4 + 672*x^5 + 448*x^6 + 
   128*x^7 + 35*y + 420*x*y + 2100*x^2*y + 5600*x^3*y + 
   8400*x^4*y + 6720*x^5*y + 2240*x^6*y + 525*y^2 + 
   5250*x*y^2 + 21000*x^2*y^2 + 42000*x^3*y^2 + 42000*x^4*y^2 + 
   16800*x^5*y^2 + 4375*y^3 + 35000*x*y^3 + 105000*x^2*y^3 + 
   140000*x^3*y^3 + 70000*x^4*y^3 + 21875*y^4 + 131250*x*y^4 + 
   262500*x^2*y^4 + 175000*x^3*y^4 + 65625*y^5 + 262500*x*y^5 + 
   262500*x^2*y^5 + 109375*y^6 + 218750*x*y^6 + 78125*y^7























































































;[o]
               2        3        4        5        6
1 + 14 x + 84 x  + 280 x  + 560 x  + 672 x  + 448 x  + 
 
        7                          2           3
   128 x  + 35 y + 420 x y + 2100 x  y + 5600 x  y + 
 
         4           5           6          2           2
   8400 x  y + 6720 x  y + 2240 x  y + 525 y  + 5250 x y  + 
 
          2  2          3  2          4  2          5  2
   21000 x  y  + 42000 x  y  + 42000 x  y  + 16800 x  y  + 
 
         3            3           2  3           3  3
   4375 y  + 35000 x y  + 105000 x  y  + 140000 x  y  + 
 
          4  3          4             4           2  4
   70000 x  y  + 21875 y  + 131250 x y  + 262500 x  y  + 
 
           3  4          5             5           2  5
   175000 x  y  + 65625 y  + 262500 x y  + 262500 x  y  + 
 
           6             6          7
   109375 y  + 218750 x y  + 78125 y
:[font = input; preserveAspect; startGroup]
Factor[%]
:[font = output; output; inactive; preserveAspect; endGroup]
(1 + 2*x + 5*y)^7























































































;[o]
               7
(1 + 2 x + 5 y)
:[font = text; inactive; preserveAspect]
With the option setting Trig -> True Mathematica  will do algebraic operations using trigonometric identities.
:[font = input; preserveAspect; startGroup]
Expand[ Cos[x]^3 Sin[x]^2, Trig -> True]
:[font = output; output; inactive; preserveAspect; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
Cos[x]   Cos[3 x]   Cos[5 x]
------ - -------- - --------
  8         16         16
:[font = text; inactive; preserveAspect]
Mathematica also does linear algebra on symbolic matrices.
:[font = input; preserveAspect; startGroup]
Inverse[{{a, b}, {c, d}}]
:[font = output; output; inactive; preserveAspect; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
       d               b
{{------------, -(------------)}, 
  -(b c) + a d    -(b c) + a d
 
          c              a
  {-(------------), ------------}}
     -(b c) + a d   -(b c) + a d
:[font = subsection; inactive; preserveAspect; startGroup]
Calculus
:[font = text; inactive; preserveAspect]
You can use Mathematica to do calculus. Here's a simple integral.
:[font = input; preserveAspect; startGroup]
Integrate[x^n, x]
:[font = output; output; inactive; preserveAspect; endGroup]
x^(1 + n)/(1 + n)



















































































;[o]
 1 + n
x
------
1 + n
:[font = text; inactive; preserveAspect]
Here's a more complicated example.
:[font = input; preserveAspect; startGroup]
Integrate[x/(x^3-1), x]
:[font = output; output; inactive; preserveAspect; endGroup]
ArcTan[(1 + 2*x)/3^(1/2)]/3^(1/2) + Log[-1 + x]/3 - 
  Log[1 + x + x^2]/6


















































;[o]
       1 + 2 x
ArcTan[-------]                              2
       Sqrt[3]    Log[-1 + x]   Log[1 + x + x ]
--------------- + ----------- - ---------------
    Sqrt[3]            3               6
:[font = text; inactive; preserveAspect]
Now let's try differentiating again.
:[font = input; preserveAspect; startGroup]
D[%, x]
:[font = output; output; inactive; preserveAspect; endGroup]
1/(3*(-1 + x)) - (1 + 2*x)/(6*(1 + x + x^2)) + 
  2/(3*(1 + (1 + 2*x)^2/3))


















































;[o]
    1           1 + 2 x               2
---------- - -------------- + ------------------
3 (-1 + x)               2                    2
             6 (1 + x + x )          (1 + 2 x)
                              3 (1 + ----------)
                                         3
:[font = text; inactive; preserveAspect]
This gives the expression in a different algebraic form. We can get back our original form using  Simplify.
:[font = input; preserveAspect; startGroup]
Simplify[%]
:[font = output; output; inactive; preserveAspect; endGroup]
x/(-1 + x^3)


















































;[o]
   x
-------
      3
-1 + x
:[font = text; inactive; preserveAspect]
Mathematica can also give exact solutions to many definite integrals.
:[font = input; preserveAspect; startGroup]
Integrate[ Sin[x]^10 Cos[x]^5/ x^10, {x, 0, Infinity}]
:[font = output; output; inactive; preserveAspect; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
40221457 Pi
-----------
 297271296
:[font = text; inactive; preserveAspect]
Many integrals do not have a "closed form solution".  If you give Mathematica such a definite integral it will be returned unevaluated. You can still use N to get a numerical answer.
:[font = input; preserveAspect; startGroup]
Integrate[ Sin[ Sin[x]], {x, 0, Pi}]
:[font = output; output; inactive; preserveAspect; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
Integrate[Sin[Sin[x]], {x, 0, Pi}]
:[font = input; preserveAspect; startGroup]
N[ % ]
:[font = output; output; inactive; preserveAspect; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
1.78649
:[font = text; inactive; preserveAspect]
Mathematica  can also solve differential equations. Here is a pair of simultaneous differential equations. The solution you get involves two undetermined coefficients.
:[font = input; preserveAspect; startGroup]
DSolve[{x'[t] == -y[t], y'[t] == x[t]}, 
                                {x[t], y[t]}, t]
:[font = output; output; inactive; preserveAspect; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
{{x[t] -> 
 
      -I t          2 I t                    2 I t
     E     (C[1] + E      C[1] - I C[2] + I E      C[2])
     ---------------------------------------------------, 
                              2
 
   y[t] -> 
 
      -I t              2 I t                2 I t
     E     (I C[1] - I E      C[1] + C[2] + E      C[2])
     ---------------------------------------------------}}
                              2
:[font = text; inactive; preserveAspect]
It is a mathematical fact that most differential equations do not have an explicit symbolic solution.  In these cases you can get a numerical approximation to the solution using NDSolve.  After the solution is computed it is plotted.
:[font = input; preserveAspect; startGroup]
NDSolve[{y''[x] + Sin[x]^2 y'[x] + y[x] == Cos[x]^2,
     y[0] == 1, y'[0] == 0}, y, {x, 0, 20}]
:[font = output; output; inactive; preserveAspect; endGroup]
{{y -> InterpolatingFunction[{0., 20.}, 
 
     {{0., 0., {1., 0}, {0}}, 
 
      {0.00833391807887338, 0., 
 
       {0.999999995176227, -(5.788121337438936*10^-7)}, 
 
       {0}}, {0.01666783615774676, 0.00833391807887338, 
 
       {0.999999971059398, -(2.893816451849014*10^-6)}, 
 
       {0}}, {0.02964841775559651, 0.01666783615774676, 
 
       {0.999999884782133, -0.00001039946535062508, 
 
        -0.0002891106551042133}, {0, 0}}, 
 
      {0.04262899935344625, 0.02964841775559651, 
 
       {0.99999963629125, -0.00002788709085687932, 
 
        -0.0006736071636863751}, {0, 0}}, 
 
      {0.05560958095129599, 0.04262899935344625, 
 
       {0.999999067729387, -0.00005971481791480953, 
 
        -0.001225974615159098}, {0, 0}}, 
 
      {0.0947110814028622, 0.05560958095129599, 
 
       {0.999992990405501, -0.0002832548310731071, 
 
        -0.004090675192305027, -0.02100886031499209}, 
 
       {0, 0, 0}}, {0.1338125818544285, 
 
       0.0947110814028622, 
 
       {0.999972842235183, -0.0007954041136751902, 
 
        -0.00839422924622937, -0.03146097510686268}, 
 
       {0, 0, 0}}, {0.1729140823059947, 
 
       0.1338125818544285, 
 
       {0.999925180045169, -0.001709448541521506, 
 
        -0.01425766259084529, -0.04381014228844968}, 
 
       {0, 0, 0}}, {0.2120155827575609, 
 
       0.1729140823059947, 
 
       {0.999832110961939, -0.003136424242784029, 
 
        -0.02152655676290291, -0.05591406698606353}, 
 
       {0, 0, 0}}, {0.2662168117990558, 
 
       0.2120155827575609, 
 
       {0.999586307461538, -0.006159345634334751, 
 
        -0.03447384405606395, -0.0894067235086784, 
 
        -0.1159353865755509}, {0, 0, 0, 0}}, 
 
      {0.3204180408405507, 0.2662168117990558, 
 
       {0.999138563793254, -0.0106296162693686, 
 
        -0.04888049336148105, -0.0999515751977139, 
 
        -0.0822864492355481}, {0, 0, 0, 0}}, 
 
      {0.3746192698820456, 0.3204180408405507, 
 
       {0.998403967137245, -0.01677476344025394, 
 
        -0.06511317173450825, -0.1079277670269854, 
 
        -0.05953808385418795}, {0, 0, 0, 0}}, 
 
      {0.4288204989235405, 0.3746192698820456, 
 
       {0.99728656711143, -0.02478296556199286, 
 
        -0.0830466014765801, -0.1163712107514024, 
 
        -0.0492414871874057}, {0, 0, 0, 0}}, 
 
      {0.4830217279650354, 0.4288204989235405, 
 
       {0.995681303650754, -0.0347966117389522, 
 
        -0.1020625494570274, -0.1218515403628469, 
 
        -0.03725959355236541}, {0, 0, 0, 0}}, 
 
      {0.5613173138914975, 0.4830217279650354, 
 
       {0.99227440929192, -0.05297494561351602, 
 
        -0.1304064567167727, -0.1222533212210629, 
 
        0.003468013152062057, 0.06814178090466316}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {0.6396128998179595, 0.5613173138914975, 
 
       {0.987269932222078, -0.07558796435988702, 
 
        -0.1581841708546058, -0.1141090179013719, 
 
        0.03802885125640521, 0.07619826041218772}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {0.7179084857444215, 0.6396128998179595, 
 
       {0.980326632358475, -0.1024529530007909, 
 
        -0.1843338202409173, -0.1041523989994629, 
 
        0.04972845417451867, 0.05767324744232547}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {0.7962040716708835, 0.7179084857444215, 
 
       {0.971126568017259, -0.1331452929673241, 
 
        -0.206933761495238, -0.0864840851602412, 
 
        0.06836174221679349, 0.05364286395141093}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {0.874499657597345, 0.7962040716708835, 
 
       {0.959393872138844, -0.1670233508919886, 
 
        -0.2248069606885577, -0.06375960618478858, 
 
        0.0839601600939627, 0.04812369770145131}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {0.952795243523808, 0.874499657597345, 
 
       {0.94491013109514, -0.2032699807620211, 
 
        -0.237016271338752, -0.03780571647621027, 
 
        0.0937923721791741, 0.03892046781243688}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {1.039724469826861, 0.952795243523808, 
 
       {0.925427687003807, -0.2451467267487646, 
 
        -0.2432953081803547, -0.00824729181196617, 
 
        0.0897846117532884, -0.001673254923511449, 
 
        -0.03900354228461007}, {0, 0, 0, 0, 0, 0}}, 
 
      {1.126653696129915, 1.039724469826861, 
 
       {0.902277860187331, -0.2874188841556198, 
 
        -0.2416891819318121, 0.02063608418995885, 
 
        0.07910389882769584, -0.031001333252597, 
 
        -0.0447456283648716}, {0, 0, 0, 0, 0, 0}}, 
 
      {1.213582922432968, 1.126653696129915, 
 
       {0.875485580138152, -0.3287280514555401, 
 
        -0.2323712829094428, 0.04893730798698263, 
 
        0.07270172261642084, -0.03461662359990179, 
 
        -0.03214091300126809}, {0, 0, 0, 0, 0, 0}}, 
 
      {1.300512148736022, 1.213582922432968, 
 
       {0.845190514398789, -0.3678083556713332, 
 
        -0.2161762060115842, 0.07364398095041834, 
 
        0.06204749522167973, -0.03833147746640562, 
 
        -0.02380139942498609}, {0, 0, 0, 0, 0, 0}}, 
 
      {1.387441375039076, 1.300512148736022, 
 
       {0.811635967162278, -0.4035503830240013, 
 
        -0.1941521090941593, 0.0937578479734978, 
 
        0.04838696543333821, -0.04149193254500012, 
 
        -0.01788741326468851}, {0, 0, 0, 0, 0, 0}}, 
 
      {1.474370601342129, 1.387441375039076, 
 
       {0.7751533548812338, -0.4350400348137605, 
 
        -0.1674659123630955, 0.1092862811264725, 
 
        0.03508465303654002, -0.03984380825355115, 
 
        -0.01087164325122934}, {0, 0, 0, 0, 0, 0}}, 
 
      {1.561299827645183, 1.474370601342129, 
 
       {0.7361445508482675, -0.4615699439190043, 
 
        -0.1372853292952238, 0.1206015647782626, 
 
        0.02393137345927295, -0.03350120050483208, 
 
        -0.003194268168556185}, {0, 0, 0, 0, 0, 0}}, 
 
      {1.681304309945809, 1.561299827645183, 
 
       {0.6789915111177365, -0.4891301489614232, 
 
        -0.0919228965395331, 0.1292768679230046, 
 
        0.00815176057943388, -0.02990506007417602, 
 
        -0.0004646944684730906}, {0, 0, 0, 0, 0, 0}}, 
 
      {1.801308792246436, 1.681304309945809, 
 
       {0.6191991919260999, -0.505457745968092, 
 
        -0.044013112283686, 0.1342605207354765, 
 
        0.002841551460762378, -0.01525917844481956, 
 
        0.006470451182786357}, {0, 0, 0, 0, 0, 0}}, 
 
      {1.921313274547062, 1.801308792246436, 
 
       {0.558142705005915, -0.5101582305936895, 
 
        0.00487414951021928, 0.1361584274616899, 
 
        0.001004286772711822, -0.003634819174967162, 
 
        0.00969508092145475}, {0, 0, 0, 0, 0, 0}}, 
 
      {2.041317756847688, 1.921313274547062, 
 
       {0.4972273697035997, -0.5030939313442149, 
 
        0.05401710825834043, 0.1367713608595788, 
 
        0.001671272832539884, 0.004232531534111378, 
 
        0.01010554323226442}, {0, 0, 0, 0, 0, 0}}, 
 
      {2.161322239148315, 2.041317756847688, 
 
       {0.4378672197416658, -0.4842377163089119, 
 
        0.1031746489028633, 0.1370225274516219, 
 
        0.002828959482405039, 0.007314965752425106, 
 
        0.00816402839834942}, {0, 0, 0, 0, 0, 0}}, 
 
      {2.281326721448941, 2.161322239148315, 
 
       {0.3814775644762603, -0.4535868073988482, 
 
        0.1523290208541011, 0.1371074550521268, 
 
        0.002822610937789769, 0.005947610747459127, 
 
        0.004809674518927994}, {0, 0, 0, 0, 0, 0}}, 
 
      {2.401331203749567, 2.281326721448941, 
 
       {0.3294736916084069, -0.4111478502856951, 
 
        0.2013732951316971, 0.1363926555241627, 
 
        0.0001288989080643669, 0.000821168850015087, 
 
        0.000833185596346208}, {0, 0, 0, 0, 0, 0}}, 
 
      {2.542772658215347, 2.401331203749567, 
 
       {0.2757297585200214, -0.3461058228824763, 
 
        0.2583852300339075, 0.1331684232942005, 
 
        -0.006068693935535207, -0.005486938116970822, 
 
        -0.001922249265772746}, {0, 0, 0, 0, 0, 0}}, 
 
      {2.684214112681127, 2.542772658215347, 
 
       {0.23230953238618, -0.2653100132041767, 
 
        0.3123095236220162, 0.1219987507622505, 
 
        -0.02360618564285771, -0.02017399774558903, 
 
        -0.007050301593520208}, {0, 0, 0, 0, 0, 0}}, 
 
      {2.825655567146907, 2.684214112681127, 
 
       {0.201358773315359, -0.1701107477311347, 
 
        0.3593398802303154, 0.0992154730256915, 
 
        -0.04999050088099612, -0.03613991564202199, 
 
        -0.01097131473295259}, {0, 0, 0, 0, 0, 0}}, 
 
      {2.940907735632757, 2.825655567146907, 
 
       {0.186666030245786, -0.0837341815516574, 
 
        0.3884126626543852, 0.0678735728459317, 
 
        -0.0807696002399574, -0.05324090820815063, 
 
        -0.01555748328956779}, {0, 0, 0, 0, 0, 0}}, 
 
      {3.056159904118607, 2.940907735632757, 
 
       {0.1822656373056615, 0.00801851043091343, 
 
        0.4051877387661725, 0.02594347709569966, 
 
        -0.1075344137538298, -0.05540545000102587, 
 
        -0.01141503998852136}, {0, 0, 0, 0, 0, 0}}, 
 
      {3.171412072604456, 3.056159904118607, 
 
       {0.1885930595327865, 0.1017818000800054, 
 
        0.4051123189032399, -0.02942474194279414, 
 
        -0.1370287052172394, -0.05753197975000251, 
 
        -0.00863508799332065}, {0, 0, 0, 0, 0, 0}}, 
 
      {3.286664241090306, 3.171412072604456, 
 
       {0.2056400155741063, 0.1932474756056508, 
 
        0.384589905243337, -0.0934917893143593, 
 
        -0.1546675134841719, -0.04239133925401135, 
 
        0.001541590227976119}, {0, 0, 0, 0, 0, 0}}, 
 
      {3.401916409576156, 3.286664241090306, 
 
       {0.2328563793698416, 0.277369133874643, 
 
        0.3410949525813243, -0.1618186780350408, 
 
        -0.1577454988899328, -0.01554859057090094, 
 
        0.01396686571139486}, {0, 0, 0, 0, 0, 0}}, 
 
      {3.517168578062005, 3.401916409576156, 
 
       {0.2690871506335883, 0.3487885752217656, 
 
        0.2745700883195514, -0.2257373664290613, 
 
        -0.1382546697400943, 0.02555136174107999, 
 
        0.02912285094693886}, {0, 0, 0, 0, 0, 0}}, 
 
      {3.632420746547855, 3.517168578062005, 
 
       {0.3125706462538578, 0.4024492684330437, 
 
        0.1878225781646507, -0.2763861795298969, 
 
        -0.0960889238980644, 0.07219643248334508, 
 
        0.04189978113679377}, {0, 0, 0, 0, 0, 0}}, 
 
      {3.725109407771678, 3.632420746547855, 
 
       {0.3512620135157215, 0.4299290818784665, 
 
        0.107412227065488, -0.300564045980854, 
 
        -0.04323714318782882, 0.1168117841126639, 
 
        0.05467465302934467}, {0, 0, 0, 0, 0, 0}}, 
 
      {3.817798068995501, 3.725109407771678, 
 
       {0.3917904952776397, 0.441930939246987, 
 
        0.0217235213163996, -0.3108504026925647, 
 
        0.00216415740363378, 0.1231829684118424, 
 
        0.04026851715680024}, {0, 0, 0, 0, 0, 0}}, 
 
      {3.910486730219324, 3.817798068995501, 
 
       {0.4326918619863334, 0.4379758288762513, 
 
        -0.06387740653734996, -0.3006019532926319, 
 
        0.05900637765257942, 0.137298754115025, 
 
        0.03530637120862066}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.003175391443148, 3.910486730219324, 
 
       {0.4725011175959778, 0.4185189358925686, 
 
        -0.1446202564060147, -0.274220686761139, 
 
        0.1031795907401829, 0.120368338254103, 
 
        0.01338985996782489}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.095864052666971, 4.003175391443148, 
 
       {0.5098377243259538, 0.3849182165598919, 
 
        -0.2157792131034731, -0.2324963289980934, 
 
        0.1390336053931838, 0.0948309615779495, 
 
        -0.00637997152264871}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.188552713890794, 4.095864052666971, 
 
       {0.5434841394600679, 0.3393013289336646, 
 
        -0.2737656374170536, -0.1802979493152825, 
 
        0.1595565707219844, 0.0568546793232653, 
 
        -0.02701546702964192}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.281241375114618, 4.188552713890794, 
 
       {0.5724477854240017, 0.2843558947412073, 
 
        -0.3161971998434309, -0.1222649423218798, 
 
        0.1653743813310553, 0.01586170222838486, 
 
        -0.04258060491772863}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.373930036338441, 4.281241375114618, 
 
       {0.5960014850760088, 0.2230777241986617, 
 
        -0.3421089612755923, -0.06332520993875242, 
 
        0.1578898632617408, -0.0220572891528992, 
 
        -0.0511148837303418}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.466618697562264, 4.373930036338441, 
 
       {0.6137001066994952, 0.158516565393035, 
 
        -0.3518415037836857, -0.00767593970421884, 
 
        0.140530006760042, -0.05132237527702079, 
 
        -0.05161744147076863}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.559307358786087, 4.466618697562264, 
 
       {0.6253749241508589, 0.0935542213594423, 
 
        -0.346799153394566, 0.04154966229532907, 
 
        0.1172911318237227, -0.06906508234439689, 
 
        -0.04504621806172066}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.65199602000991, 4.559307358786087, 
 
       {0.6311098831147893, 0.03073885364812035, 
 
        -0.3291073644772311, 0.082472480789486, 
 
        0.0919731868613143, -0.07508555495420271, 
 
        -0.0336393515985056}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.744684681233733, 4.65199602000991, 
 
       {0.6312055641438369, -0.02781417648852856, 
 
        -0.3012492144753387, 0.1144260245088299, 
 
        0.06763696782833857, -0.07125622832213287, 
 
        -0.02013101979951132}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.837373342457556, 4.744684681233733, 
 
       {0.6261369840398518, -0.0804574507746193, 
 
        -0.2657459421947091, 0.137717486393185, 
 
        0.04625548308657163, -0.0607685499100978, 
 
        -0.007134594891376783}, {0, 0, 0, 0, 0, 0}}, 
 
      {4.934711057745936, 4.837373342457556, 
 
       {0.6159201087101637, -0.1280753363299494, 
 
        -0.2227806802102572, 0.1537211920705846, 
 
        0.02696973423293975, -0.04854088561810296, 
 
        0.002222549653624287}, {0, 0, 0, 0, 0, 0}}, 
 
      {5.032048773034315, 4.934711057745936, 
 
       {0.6014883015401244, -0.166943530383837, 
 
        -0.1761636801829143, 0.1632626749538186, 
 
        0.01413699514628434, -0.03204915206233514, 
 
        0.01089436593913279}, {0, 0, 0, 0, 0, 0}}, 
 
      {5.129386488322694, 5.032048773034315, 
 
       {0.5837216041415593, -0.1965367995815976, 
 
        -0.1277013131725531, 0.167420407459309, 
 
        0.004421784123874849, -0.01939997453232677, 
 
        0.01448243579080505}, {0, 0, 0, 0, 0, 0}}, 
 
      {5.226724203611073, 5.129386488322694, 
 
       {0.5635360668562096, -0.2166241743055614, 
 
        -0.07865201517038877, 0.1679556785839339, 
 
        -0.001842232207100047, -0.00931816238936085, 
 
        0.01540915712500491}, {0, 0, 0, 0, 0, 0}}, 
 
      {5.324061918899452, 5.226724203611073, 
 
       {0.5418594791619583, -0.2271828519981813, 
 
        -0.02990388020120234, 0.1659242665342605, 
 
        -0.006270015173674181, -0.003339483838347045, 
 
        0.01368510555052145}, {0, 0, 0, 0, 0, 0}}, 
 
      {5.421399634187832, 5.324061918899452, 
 
       {0.5196144746405613, -0.2283325924656537, 
 
        0.01793903086638241, 0.1620672792763998, 
 
        -0.0098851433324087, -0.001053910396611507, 
 
        0.01042789602320987}, {0, 0, 0, 0, 0, 0}}, 
 
      {5.518737349476211, 5.421399634187832, 
 
       {0.4977068276780475, -0.2202955529216872, 
 
        0.06440879954566531, 0.1566328278083659, 
 
        -0.01380086394227507, -0.002048552973040461, 
 
        0.006384237874177359}, {0, 0, 0, 0, 0, 0}}, 
 
      {5.649738466072061, 5.518737349476211, 
 
       {0.4702987123866805, -0.1955400310684071, 
 
        0.1240203696855997, 0.1472188891324853, 
 
        -0.01807952334630211, -0.001966538751663095, 
 
        0.004290939550691715}, {0, 0, 0, 0, 0, 0}}, 
 
      {5.78073958266791, 5.649738466072061, 
 
       {0.4471299527936173, -0.1557874345092917, 
 
        0.1786320840253281, 0.1319216673073173, 
 
        -0.03054559452166536, -0.01183041201878553, 
 
        -0.001322490338473921}, {0, 0, 0, 0, 0, 0}}, 
 
      {5.91174069926376, 5.78073958266791, 
 
       {0.4300686591607996, -0.1026166041232121, 
 
        0.2259225305799294, 0.1090694942794363, 
 
        -0.04892237221187041, -0.02395631347686872, 
 
        -0.006024068241865188}, {0, 0, 0, 0, 0, 0}}, 
 
      {6.04274181585961, 5.91174069926376, 
 
       {0.4207288145946937, -0.03837638011391281, 
 
        0.2623942050935006, 0.07574889260908665, 
 
        -0.07330840755912167, -0.03638996218237689, 
 
        -0.00928896437776595}, {0, 0, 0, 0, 0, 0}}, 
 
      {6.148769256036683, 6.04274181585961, 
 
       {0.4196897500697935, 0.01943482114196284, 
 
        0.2809613885730516, 0.03966871725008556, 
 
        -0.0959756635929291, -0.04470825276025536, 
 
        -0.01055120569768173}, {0, 0, 0, 0, 0, 0}}, 
 
      {6.254796696213755, 6.148769256036683, 
 
       {0.4249451047555365, 0.07991307767549888, 
 
        0.2870681658288904, -0.003651896803221795, 
 
        -0.114345095524018, -0.04195180205795542, 
 
        -0.005589830357314855}, {0, 0, 0, 0, 0, 0}}, 
 
      {6.360824136390828, 6.254796696213755, 
 
       {0.4366274857406315, 0.1401379550208859, 
 
        0.2781781536190101, -0.05444083104471375, 
 
        -0.130699368394894, -0.03641559880016736, 
 
        -0.00082573047196417}, {0, 0, 0, 0, 0, 0}}, 
 
      {6.466851576567901, 6.360824136390828, 
 
       {0.4545348154762829, 0.1967456551708997, 
 
        0.2526968342022333, -0.1083739882512972, 
 
        -0.1351493159917838, -0.0169755150025494, 
 
        0.0096355993150261}, {0, 0, 0, 0, 0, 0}}, 
 
      {6.572879016744973, 6.466851576567901, 
 
       {0.478093112157012, 0.2461236474037912, 
 
        0.210019709423567, -0.1617183745404226, 
 
        -0.1274191024144098, 0.00926640796546203, 
 
        0.02017380179185549}, {0, 0, 0, 0, 0, 0}}, 
 
      {6.678906456922046, 6.572879016744973, 
 
       {0.5063450944739293, 0.2847147407399766, 
 
        0.1513125419919496, -0.2081509114353682, 
 
        -0.1028453481500758, 0.04374984632893029, 
 
        0.0315176047375476}, {0, 0, 0, 0, 0, 0}}, 
 
      {6.784933897099119, 6.678906456922046, 
 
       {0.5379757930463986, 0.3093945084396734, 
 
        0.07953745385550099, -0.2420209273690898, 
 
        -0.06343949404947561, 0.07831062270695172, 
 
        0.03912066309509183}, {0, 0, 0, 0, 0, 0}}, 
 
      {6.890961337276191, 6.784933897099119, 
 
       {0.5713794688494059, 0.3178661444858388, 
 
        -0.0005288975609562471, -0.2582209592055742, 
 
        -0.01273748829296852, 0.1066461744790181, 
 
        0.04092751672672077}, {0, 0, 0, 0, 0, 0}}, 
 
      {6.996988777453264, 6.890961337276191, 
 
       {0.6047659964055994, 0.3089937460130625, 
 
        -0.0828378376281369, -0.2540816900067733, 
 
        0.04155322845163591, 0.1205023264885455, 
 
        0.03454526586884976}, {0, 0, 0, 0, 0, 0}}, 
 
      {7.103016217630337, 6.996988777453264, 
 
       {0.6362958458843098, 0.2829988296174894, 
 
        -0.1608247747340724, -0.2296920371300753, 
 
        0.0909423127802832, 0.1154239347165921, 
 
        0.02036923517516369}, {0, 0, 0, 0, 0, 0}}, 
 
      {7.209043657807409, 7.103016217630337, 
 
       {0.6642262249640803, 0.241468456324935, 
 
        -0.2283657429491444, -0.1881349903293973, 
 
        0.1275788739306763, 0.0911148293495092, 
 
        0.000842166161096067}, {0, 0, 0, 0, 0, 0}}, 
 
      {7.315071097984482, 7.209043657807409, 
 
       {0.6870474825141311, 0.1871666952365883, 
 
        -0.2806327954905358, -0.1346980858598619, 
 
        0.146806830007886, 0.0527473379786332, 
 
        -0.01954209979427752}, {0, 0, 0, 0, 0, 0}}, 
 
      {7.421098538161555, 7.315071097984482, 
 
       {0.703590616186642, 0.123687995999632, 
 
        -0.3146862203161755, -0.07576121253554057, 
 
        0.1479876662116295, 0.00904198604132872, 
 
        -0.03592850724159229}, {0, 0, 0, 0, 0, 0}}, 
 
      {7.508548200649285, 7.421098538161555, 
 
       {0.7119569421005507, 0.06725267497209354, 
 
        -0.3284763886951176, -0.02837686383834428, 
 
        0.1329404504265283, -0.03383053167086992, 
 
        -0.0511886529941892}, {0, 0, 0, 0, 0, 0}}, 
 
      {7.595997863137016, 7.508548200649285, 
 
       {0.71531594977949, 0.009536660797805, 
 
        -0.3295549730887778, 0.01821714030880542, 
 
        0.1231736125883193, -0.04309656930130623, 
 
        -0.04001235362736938}, {0, 0, 0, 0, 0, 0}}, 
 
      {7.683447525624747, 7.595997863137016, 
 
       {0.713648770586855, -0.04737048168354879, 
 
        -0.3194709080046897, 0.05732411009775225, 
 
        0.0992602176210202, -0.06486523951098413, 
 
        -0.04050423614922553}, {0, 0, 0, 0, 0, 0}}, 
 
      {7.770897188112478, 7.683447525624747, 
 
       {0.7071083616118942, -0.1016343814651762, 
 
        -0.2996825389237404, 0.0907012234234826, 
 
        0.07972445791576242, -0.06436764782411768, 
 
        -0.02668671101434088}, {0, 0, 0, 0, 0, 0}}, 
 
      {7.858346850600208, 7.770897188112478, 
 
       {0.6959947989837136, -0.1517340681807, 
 
        -0.272159893062094, 0.116583488776321, 
 
        0.05912674709439149, -0.06124448947359819, 
 
        -0.01580704156920753}, {0, 0, 0, 0, 0, 0}}, 
 
      {7.945796513087939, 7.858346850600208, 
 
       {0.6807265961198544, -0.1964816008608662, 
 
        -0.2387811299207966, 0.135523738205701, 
 
        0.0407495675344065, -0.05304738380436073, 
 
        -0.005330520371470538}, {0, 0, 0, 0, 0, 0}}, 
 
      {8.03324617557567, 7.945796513087939, 
 
       {0.6618120211003451, -0.2350071462003558, 
 
        -0.2012652124776912, 0.1484735118590915, 
 
        0.02609187563055317, -0.04107493734528286, 
 
        0.004052248643005174}, {0, 0, 0, 0, 0, 0}}, 
 
      {8.13281647839931, 8.03324617557567, 
 
       {0.6365666256831777, -0.2705578217346456, 
 
        -0.1554143999562103, 0.1567841171352484, 
 
        0.01168305239374725, -0.0309440518330866, 
 
        0.00835405770909028}, {0, 0, 0, 0, 0, 0}}, 
 
      {8.23238678122294, 8.13281647839931, 
 
       {0.6082431420500708, -0.2967750336585689, 
 
        -0.1077494565994657, 0.1608984816289444, 
 
        0.004247223612137885, -0.01641473156726737, 
 
        0.01367605056791856}, {0, 0, 0, 0, 0, 0}}, 
 
      {8.33195708404658, 8.23238678122294, 
 
       {0.5777841210540379, -0.3134339054870631, 
 
        -0.05953811389777338, 0.1614440284889166, 
 
        -0.001289667326287109, -0.007391672265127047, 
 
        0.01415181063062216}, {0, 0, 0, 0, 0, 0}}, 
 
      {8.43152738687022, 8.33195708404658, 
 
       {0.5461440693743184, -0.3205041964959894, 
 
        -0.01153044773665265, 0.1599897846433574, 
 
        -0.004251321791737685, -0.000902600555799919, 
 
        0.01305513786391204}, {0, 0, 0, 0, 0, 0}}, 
 
      {8.53109768969385, 8.43152738687022, 
 
       {0.5142738769023971, -0.3180795928030647, 
 
        0.03577363033590608, 0.1571229560031264, 
 
        -0.006577584432921674, 0.00148290725363588, 
 
        0.01003442663435897}, {0, 0, 0, 0, 0, 0}}, 
 
      {8.63066799251749, 8.53109768969385, 
 
       {0.4831108462439235, -0.3063344545470925, 
 
        0.0820313240336581, 0.1531175103396056, 
 
        -0.00927229609602591, 0.000494411646538026, 
 
        0.006138083603392371}, {0, 0, 0, 0, 0, 0}}, 
 
      {8.73023829534112, 8.63066799251749, 
 
       {0.4535716026520096, -0.2855097744189809, 
 
        0.12689662282007, 0.1477532309109715, 
 
        -0.01335343895191015, -0.003268281664602612, 
 
        0.001992649473309566}, {0, 0, 0, 0, 0, 0}}, 
 
      {8.86853706070109, 8.73023829534112, 
 
       {0.4168957667028229, -0.2421465923824881, 
 
        0.1860339196599167, 0.1376415505493594, 
 
        -0.0195590038827496, -0.006226046386205811, 
 
        0.0001402787576651009}, {0, 0, 0, 0, 0, 0}}, 
 
      {9.00683582606106, 8.86853706070109, 
 
       {0.3873133091376599, -0.1832009593671674, 
 
        0.239160465511783, 0.1194158489198192, 
 
        -0.0365717901244306, -0.01912265738851763, 
 
        -0.00508713726541164}, {0, 0, 0, 0, 0, 0}}, 
 
      {9.14513459142103, 9.00683582606106, 
 
       {0.3668459726947033, -0.1107675983000213, 
 
        0.2827664638947727, 0.0904741309946076, 
 
        -0.061054976085714, -0.03334834958597526, 
 
        -0.00910598224500634}, {0, 0, 0, 0, 0, 0}}, 
 
      {9.25551848121334, 9.14513459142103, 
 
       {0.3581747805269447, -0.04543522822260938, 
 
        0.3073147680177094, 0.05682751623234028, 
 
        -0.0872347770888308, -0.04719359211012947, 
 
        -0.01303888308938168}, {0, 0, 0, 0, 0, 0}}, 
 
      {9.36590237100565, 9.25551848121334, 
 
       {0.3569689170381058, 0.02404370836295861, 
 
        0.3197343473169469, 0.01539494283162508, 
 
        -0.1074876929931563, -0.04537092074234303, 
 
        -0.007775249061715535}, {0, 0, 0, 0, 0, 0}}, 
 
      {9.47628626079796, 9.36590237100565, 
 
       {0.3635240166384773, 0.0946146960092761, 
 
        0.3166975493235761, -0.03606205004272803, 
 
        -0.1294834655084761, -0.04480815684421426, 
 
        -0.004900263649898211}, {0, 0, 0, 0, 0, 0}}, 
 
      {9.58667015059027, 9.47628626079796, 
 
       {0.3777627203103866, 0.1626036262707624, 
 
        0.2959259365453351, -0.0926247985683177, 
 
        -0.1391946024880197, -0.02769901997419429, 
 
        0.005344084448169803}, {0, 0, 0, 0, 0, 0}}, 
 
      {9.69705404038259, 9.58667015059027, 
 
       {0.3991762427622853, 0.2239075675261893, 
 
        0.2560371407258804, -0.1507645013479063, 
 
        -0.1366905839515919, -0.002654030290633712, 
 
        0.01616771739326827}, {0, 0, 0, 0, 0, 0}}, 
 
      {9.8074379301749, 9.69705404038259, 
 
       {0.4267941323519698, 0.274335877093371, 
 
        0.1976733541860351, -0.2032950372891713, 
 
        -0.1154060624976974, 0.03337009604233589, 
 
        0.0289092069001821}, {0, 0, 0, 0, 0, 0}}, 
 
      {9.91782181996721, 9.8074379301749, 
 
       {0.4591991416097382, 0.31006500744825, 
 
        0.1236039062949651, -0.2435754024315093, 
 
        -0.07673555065351283, 0.07166175178200006, 
 
        0.03854476730918791}, {0, 0, 0, 0, 0, 0}}, 
 
      {10.02820570975952, 9.91782181996721, 
 
       {0.4945953805715523, 0.3281412695479749, 
 
        0.0388792343653136, -0.2652284114586495, 
 
        -0.0238251151861789, 0.1052655940026059, 
 
        0.04260912557108553}, {0, 0, 0, 0, 0, 0}}, 
 
      {10.11839827252234, 10.02820570975952, 
 
       {0.5243110444081898, 0.3286160469448058, 
 
        -0.03363083760883982, -0.2670957301326402, 
 
        0.0192861181114368, 0.1013986364282207}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.20859083528516, 10.11839827252234, 
 
       {0.5534807333021167, 0.3160777660600384, 
 
        -0.1047203943665232, -0.2545006870116073, 
 
        0.05880905480378934, 0.0958956747487351}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.28195018405387, 10.20859083528516, 
 
       {0.5760057416617506, 0.2967065067954779, 
 
        -0.1585198769214696, -0.2325092428508429, 
 
        0.0927387732130284, 0.0945385167497419}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.35530953282258, 10.28195018405387, 
 
       {0.5968292777189319, 0.2698290112235276, 
 
        -0.2066914351746289, -0.2025565359917684, 
 
        0.1205565981009465, 0.0870590662488251}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.4286688815913, 10.35530953282258, 
 
       {0.6154342049194712, 0.2363997816128122, 
 
        -0.2475513359275141, -0.1657912266098145, 
 
        0.142862789994474, 0.07656083769016658}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.50202823036002, 10.4286688815913, 
 
       {0.6313813965423457, 0.1975780454926408, 
 
        -0.2799942991097723, -0.1252916923540397, 
 
        0.1547568061078292, 0.05890719149069205}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.57538757912873, 10.50202823036002, 
 
       {0.6443221540461277, 0.1546586693456827, 
 
        -0.3033196518785068, -0.0829505722376567, 
 
        0.1581351288998066, 0.03902845097106279}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.64874692789745, 10.57538757912873, 
 
       {0.6540055443030332, 0.1090001923582619, 
 
        -0.3173369421741141, -0.04094805976329566, 
 
        0.1534621123856937, 0.01832104223173633}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.74639726430494, 10.64874692789745, 
 
       {0.6615948197635895, 0.04629678861693287, 
 
        -0.3218307437056929, 0.01381803967824749, 
 
        0.1472144944432427, 0.005874266777425117}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.82722328921276, 10.74639726430494, 
 
       {0.6632428652358641, -0.005324693948686697, 
 
        -0.3149970427674093, 0.04841183072594543, 
 
        0.1141021501424301, -0.02924938306740853}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.90804931412058, 10.82722328921276, 
 
       {0.6607845453432216, -0.05507703332568294, 
 
        -0.2991461034815827, 0.0822019249178615, 
 
        0.0991298994191561, -0.03236886732277859}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {10.98887533902839, 10.90804931412058, 
 
       {0.6544243258835416, -0.1016853448350176, 
 
        -0.27632707128027, 0.1073568244201017, 
 
        0.07337701707591333, -0.04491101371718704}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.06970136393621, 10.98887533902839, 
 
       {0.6444596000566764, -0.1441177027463599, 
 
        -0.247823307231611, 0.1271821073571082, 
 
        0.05267179378945773, -0.04744022850677176}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.15052738884403, 11.06970136393621, 
 
       {0.6312615072564931, -0.181580870207822, 
 
        -0.2151153184375349, 0.1414296542575301, 
 
        0.03434215236959503, -0.04660645345899256}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.23135341375184, 11.15052738884403, 
 
       {0.6152562971402444, -0.2134996441724706, 
 
        -0.1794555117091846, 0.1510248353351531, 
 
        0.01958183880383496, -0.04257333835321336}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.31217943865966, 11.23135341375184, 
 
       {0.5969089166472578, -0.2394860235316556, 
 
        -0.1418951931899485, 0.1568686820601939, 
 
        0.0084590695404782, -0.03655309992203092}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.39300546356748, 11.31217943865966, 
 
       {0.5767092909920609, -0.259304010702524, 
 
        -0.1032657867082248, 0.1598347008295249, 
 
        0.0006306763666456242, -0.02968024860181139}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.47383148847529, 11.39300546356748, 
 
       {0.5551614629849471, -0.2728347304514807, 
 
        -0.06419183399953953, 0.1606951551808608, 
 
        -0.004482773708489445, -0.02286934085617966}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.59181245838549, 11.47383148847529, 
 
       {0.5223442673650719, -0.2812468845494471, 
 
        -0.007394887061569753, 0.1580464444945923, 
 
        -0.01316017086058137, -0.01960553454662527}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.70979342829569, 11.59181245838549, 
 
       {0.4893232303269613, -0.276324227089577, 
 
        0.04865287774705832, 0.1551563588458628, 
 
        -0.01373801045783469, -0.01215513957651872}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.80584992313915, 11.70979342829569, 
 
       {0.463367766143657, -0.2626733185366273, 
 
        0.0931804529812173, 0.152081938861541, 
 
        -0.01215702452953191, -0.005976370342300691}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.9019064179826, 11.80584992313915, 
 
       {0.4391290731553842, -0.2406284706132639, 
 
        0.1360586486077004, 0.1457848981087284, 
 
        -0.01742454314486029, -0.007972839344752792}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {11.99796291282605, 11.9019064179826, 
 
       {0.417397984390547, -0.2105260018435069, 
 
        0.1769646555446012, 0.1380169033649815, 
 
        -0.02200641933953245, -0.00859968810817195}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {12.09401940766951, 11.99796291282605, 
 
       {0.3989273560299016, -0.1728332477520758, 
 
        0.2149743443228919, 0.1264940676148489, 
 
        -0.03120942203901245, -0.01282447122203212}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {12.19007590251296, 12.09401940766951, 
 
       {0.3844166644238102, -0.1282029524520043, 
 
        0.2489947840812819, 0.1104537136973883, 
 
        -0.04376143153927142, -0.01814853868181721}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {12.28613239735642, 12.19007590251296, 
 
       {0.3744912322418332, -0.07753790645080543, 
 
        0.2775396899026297, 0.088559491981706, 
 
        -0.06007473491549192, -0.02447554724241082}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {12.40706782184305, 12.28613239735642, 
 
       {0.3693118085400968, -0.007084303485583878, 
 
        0.3030209527539531, 0.05227618350805328, 
 
        -0.0813296005269796, -0.02874563578751356}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {12.50286087032136, 12.40706782184305, 
 
       {0.3714489883600176, 0.05200117912645592, 
 
        0.3121156277699795, 0.01263947147471424, 
 
        -0.1081519794114174, -0.0396476531502687}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {12.59865391879967, 12.50286087032136, 
 
       {0.3792971236981668, 0.111780050932998, 
 
        0.3097805285586872, -0.03080410622624987, 
 
        -0.1234833951459268, -0.0365923733570227}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {12.69444696727798, 12.59865391879967, 
 
       {0.3928102338997812, 0.1698466988871758, 
 
        0.2939664077884001, -0.0807040980195203, 
 
        -0.1392801578858477, -0.03514783243286639}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {12.79024001575629, 12.69444696727798, 
 
       {0.4116981978553735, 0.223537891965976, 
 
        0.2638569200779901, -0.1321307654710993, 
 
        -0.1445565957751869, -0.02549523039126975}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {12.8860330642346, 12.79024001575629, 
 
       {0.4354094201374734, 0.2700866444583749, 
 
        0.2193753389441983, -0.1817095105555567, 
 
        -0.1385347712610682, -0.01026810983675054}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {12.98182611271291, 12.8860330642346, 
 
       {0.4631304990043235, 0.306834098262182, 
 
        0.1617421661587076, -0.2245100395457581, 
 
        -0.118344299213047, 0.01070087708974334}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.07761916119122, 12.98182611271291, 
 
       {0.4938082232892331, 0.3314745865392271, 
 
        0.0934657994572556, -0.2558921935390551, 
 
        -0.0841783439375117, 0.03495366582358951}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.17341220966953, 13.07761916119122, 
 
       {0.5261953239512033, 0.3423015075126858, 
 
        0.0182739582029977, -0.2719144759231259, 
 
        -0.03830014211971284, 0.05928663048603258}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.26920525814784, 13.17341220966953, 
 
       {0.5589182084944869, 0.3384127185576852, 
 
        -0.05922268640454944, -0.2702690348932502, 
 
        0.01443576561644617, 0.07961351038513034}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.34529055187567, 13.26920525814784, 
 
       {0.5842073709538621, 0.3248259011961249, 
 
        -0.1189644830088055, -0.2545406983028982, 
 
        0.06366625855077272, 0.0995315843196925}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.4213758456035, 13.34529055187567, 
 
       {0.6081225950046207, 0.3023995396551641, 
 
        -0.1748494778076072, -0.2320666441711016, 
 
        0.094027666029851, 0.0916424995369639}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.49746113933132, 13.4213758456035, 
 
       {0.6300194824839213, 0.2719342290679805, 
 
        -0.2242750099020841, -0.1989088658322016, 
 
        0.1267395425064943, 0.0893804529373537}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.57354643305915, 13.49746113933132, 
 
       {0.6493267164650191, 0.2345332766000088, 
 
        -0.2656579502062963, -0.1599261711453637, 
 
        0.14732615417011, 0.07527409649932756}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.64963172678698, 13.57354643305915, 
 
       {0.665566138016141, 0.1915305391075872, 
 
        -0.2977012469181013, -0.1167907706340484, 
 
        0.1588961976668887, 0.05732979806445648}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.7257170205148, 13.64963172678698, 
 
       {0.6783671989656641, 0.1444058888068453, 
 
        -0.3197451816398104, -0.07221181967471149, 
 
        0.1607138101057617, 0.03630901025429279}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.81616258819463, 13.7257170205148, 
 
       {0.6887658255837544, 0.0851508631088288, 
 
        -0.3326974156045494, -0.01891700976409851, 
 
        0.1586092280983821, 0.01992388253224336}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.90660815587446, 13.81616258819463, 
 
       {0.6937358850492169, 0.02477139599492429, 
 
        -0.3323082713154118, 0.02765850593813655, 
 
        0.1381548898560034, -0.006137735148575775}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {13.99705372355428, 13.90660815587446, 
 
       {0.6932826508491781, -0.03441176333102152, 
 
        -0.3198776263539699, 0.06780107892728637, 
 
        0.1132570294633304, -0.02570504508869633}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {14.08749929123411, 13.99705372355428, 
 
       {0.6876075804241283, -0.0903965565799964, 
 
        -0.2974050114132563, 0.1000010309117915, 
 
        0.0858258481724401, -0.03968617846644862}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {14.17794485891393, 14.08749929123411, 
 
       {0.6770763790316279, -0.1415577630879575, 
 
        -0.2670218318123456, 0.1243495098770493, 
 
        0.05969227068124454, -0.04692711508633564}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {14.26839042659376, 14.17794485891393, 
 
       {0.6621841356134306, -0.1866577814314705, 
 
        -0.2308227828888025, 0.1414656036691544, 
 
        0.03695259635478571, -0.04826973611031466}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {14.35883599427358, 14.26839042659376, 
 
       {0.6435212479220484, -0.2248194878183653, 
 
        -0.1906687972875908, 0.1524985996570817, 
 
        0.01894490192173852, -0.04488982566363032}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {14.44928156195341, 14.35883599427358, 
 
       {0.6217430201511704, -0.2554748980607995, 
 
        -0.1481013370578227, 0.1587310661802943, 
 
        0.005872272212059783, -0.03849676579196503}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {14.53972712963324, 14.44928156195341, 
 
       {0.5975444966274824, -0.2783025332464532, 
 
        -0.1043070148307139, 0.1614283349908202, 
 
        -0.002718849487008802, -0.03069699166783737}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {14.65457731588352, 14.53972712963324, 
 
       {0.564453415701801, -0.2958067422271722, 
 
        -0.04836626232435052, 0.1601964949958227, 
 
        -0.01275977010738678, -0.02541229466963741}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {14.7694275021338, 14.65457731588352, 
 
       {0.5300898218439486, -0.3004797616893776, 
 
        0.007220635111907524, 0.1582393591312099, 
 
        -0.01381329485306089, -0.01598121957910507}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {14.88427768838409, 14.7694275021338, 
 
       {0.4959170395194875, -0.2925288644854194, 
 
        0.06156880599668268, 0.1545072161616611, 
 
        -0.0141808344491966, -0.00984474498218937}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {14.97987154134161, 14.88427768838409, 
 
       {0.468650954740557, -0.2765293184156569, 
 
        0.1055192149929242, 0.1506461309071294, 
 
        -0.0133697933977559, -0.005228107906120798}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {15.07546539429913, 14.97987154134161, 
 
       {0.4433104036718056, -0.2522965962162206, 
 
        0.1477005573872722, 0.1438724362990859, 
 
        -0.01852199234982838, -0.007448605545133502}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {15.17105924725665, 15.07546539429913, 
 
       {0.4206656745983199, -0.2201971369607098, 
 
        0.1877116574685842, 0.1352880598951839, 
 
        -0.02392336714397008, -0.00898943288587028}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {15.26665310021416, 15.17105924725665, 
 
       {0.4014460561426418, -0.1807349167373639, 
 
        0.2246024356988366, 0.122794433225932, 
 
        -0.03387891003579053, -0.01372519368171842}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {15.36224695317168, 15.26665310021416, 
 
       {0.3863237934223654, -0.1346085571414309, 
 
        0.2572178676710811, 0.1055478176556446, 
 
        -0.04724901239701226, -0.01942420583985769}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {15.4578408061292, 15.36224695317168, 
 
       {0.3758924954820636, -0.0827759254539078, 
 
        0.284022798214581, 0.0822694927674704, 
 
        -0.06423641085579828, -0.02587083380690877}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {15.57832997722123, 15.4578408061292, 
 
       {0.3701675787906939, -0.01133018055937743, 
 
        0.3068138945102135, 0.04407354687910712, 
 
        -0.0860129222621488, -0.02998123459793272}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {15.67090937857318, 15.57832997722123, 
 
       {0.3717737373947385, 0.04623432231547501, 
 
        0.3133356255584845, 0.004318665856773654, 
 
        -0.1122352363961976, -0.04064804833787079}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {15.76348877992514, 15.67090937857318, 
 
       {0.3787359887372894, 0.1040349728410311, 
 
        0.3089349183309694, -0.03823708801793726, 
 
        -0.1252857312699313, -0.03566606318929518}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {15.85606818127709, 15.76348877992514, 
 
       {0.3909761504728071, 0.1598613020799926, 
 
        0.2917944198238701, -0.0866545966617963, 
 
        -0.1393999982106057, -0.03359610212445872}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {15.94864758262905, 15.85606818127709, 
 
       {0.4082007856563776, 0.2113010662785598, 
 
        0.2613538001774777, -0.13580316193614, 
 
        -0.1425609063113617, -0.02288907500567509}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.041226983981, 15.94864758262905, 
 
       {0.4298890079711116, 0.2558776355729043, 
 
        0.2176690032460036, -0.1827656258177188, 
 
        -0.135120337198658, -0.007303877299101397}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.13380638533296, 16.041226983981, 
 
       {0.4552945477814039, 0.2912331533504202, 
 
        0.1619622329037247, -0.2230409814172722, 
 
        -0.114456833713775, 0.01347348446669285}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.22638578668491, 16.13380638533296, 
 
       {0.4834662791897442, 0.3153351684113752, 
 
        0.096561065753054, -0.2525521744419809, 
 
        -0.0810941339920989, 0.03691357044287828}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.31896518803687, 16.22638578668491, 
 
       {0.5132879274980163, 0.3266808895618128, 
 
        0.02483845613320344, -0.2678343819560982, 
 
        -0.03719320658411754, 0.06008394808593143}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.41154458938882, 16.31896518803687, 
 
       {0.5435353503689078, 0.3244663907129619, 
 
        -0.04908147926218418, -0.266861255351552, 
 
        0.01283205850571205, 0.07927835638165857}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.48753945004418, 16.41154458938882, 
 
       {0.567795045061648, 0.3124891397927106, 
 
        -0.108163784020283, -0.252454043527386, 
 
        0.05967130092316843, 0.0968748138707661}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.56353431069953, 16.48753945004418, 
 
       {0.5908088072069284, 0.2917746710191372, 
 
        -0.1635235113104819, -0.2305440091414292, 
 
        0.091339313463593, 0.0914618927117879}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.63952917135489, 16.56353431069953, 
 
       {0.6119395782531193, 0.2630901317767555, 
 
        -0.2126809822576071, -0.1985002140394412, 
 
        0.1232625834660511, 0.0884828502526798}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.71552403201025, 16.63952917135489, 
 
       {0.6306205969669651, 0.227503351563336, 
 
        -0.2540091966204563, -0.1604206510317237, 
 
        0.1441955100482017, 0.07512585986134544}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.7915188926656, 16.71552403201025, 
 
       {0.6463754607144296, 0.1863127728519648, 
 
        -0.2862167927125299, -0.1181473495245304, 
 
        0.1561109995216934, 0.05761898464165141}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.86751375332096, 16.7915188926656, 
 
       {0.6588326244617818, 0.1409649996469174, 
 
        -0.3086204009706546, -0.07430528669484194, 
 
        0.1584354748145546, 0.03701837235486613}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {16.95733739642129, 16.86751375332096, 
 
       {0.6689572751896313, 0.0840678487829149, 
 
        -0.3221796087464951, -0.02205249277894237, 
 
        0.156789676112907, 0.02074521895891035}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {17.04716103952161, 16.95733739642129, 
 
       {0.6738972039598402, 0.02591673047498206, 
 
        -0.3227000215554471, 0.023910050869923, 
 
        0.1373720452204115, -0.004846872940027093}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {17.13698468262194, 17.04716103952161, 
 
       {0.673643007758177, -0.0312313524514592, 
 
        -0.3113964021960857, 0.06376095893028464, 
 
        0.1134500862947989, -0.02421383624676905}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {17.22680832572227, 17.13698468262194, 
 
       {0.6683752870135838, -0.0854178972737373, 
 
        -0.2901661787240669, 0.0959810472238923, 
 
        0.0868580556937173, -0.03821207112836109}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {17.3166319688226, 17.22680832572227, 
 
       {0.6584347614595486, -0.135041193034558, 
 
        -0.261050230765095, 0.1205935659354072, 
 
        0.06131787754017379, -0.04567419639388285}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {17.40645561192293, 17.3166319688226, 
 
       {0.6442893490370993, -0.1788695361084319, 
 
        -0.2260696587580292, 0.1381433886594438, 
 
        0.03890524972460628, -0.04736596855641121}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {17.49627925502325, 17.40645561192293, 
 
       {0.6265017406768527, -0.2160179646356895, 
 
        -0.1870341051036989, 0.1497019527096854, 
 
        0.02097636220741157, -0.04438765982526117}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {17.58610289812358, 17.49627925502325, 
 
       {0.6057002439648129, -0.245901305443197, 
 
        -0.1454555031001221, 0.1564862266409059, 
 
        0.007789766941586045, -0.03837702737059506}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {17.67592654122391, 17.58610289812358, 
 
       {0.5825544391258384, -0.2681756965270243, 
 
        -0.1025098888816065, 0.1597119263944868, 
 
        -0.001040900678105992, -0.03089111017629093}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {17.78809170159423, 17.67592654122391, 
 
       {0.5514133736140936, -0.2850738500576738, 
 
        -0.04835123972634359, 0.1592311279237783, 
 
        -0.01096697297056383, -0.02561427782098866}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {17.90025686196456, 17.78809170159423, 
 
       {0.5190589881547762, -0.2898197191294092, 
 
        0.005647420012884109, 0.1577758440097412, 
 
        -0.01255561071259537, -0.01650163705903245}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {18.01242202233488, 17.90025686196456, 
 
       {0.4868472127587961, -0.2825671416761103, 
 
        0.05862262850782773, 0.154497475987126, 
 
        -0.01334512130186909, -0.01046408798887593}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {18.1245871827052, 18.01242202233488, 
 
       {0.4561086913750619, -0.2636069909283319, 
 
        0.1100284649254645, 0.1495782470021582, 
 
        -0.01465782344755384, -0.007214716525379766}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {18.23675234307553, 18.1245871827052, 
 
       {0.4281341145803898, -0.2333660656866641, 
 
        0.1591629018821707, 0.1421370514778151, 
 
        -0.01862210623256728, -0.007156290971352536}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {18.34891750344585, 18.23675234307553, 
 
       {0.4041563857379967, -0.1924613401132125, 
 
        0.2049808679143771, 0.1307855834048111, 
 
        -0.02664008124564614, -0.01001246654499059}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {18.46108266381618, 18.34891750344585, 
 
       {0.3853239721464061, -0.1418019932591391, 
 
        0.2458898396181167, 0.1136312949385891, 
 
        -0.03978681725031479, -0.015384178332284}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {18.5732478241865, 18.46108266381618, 
 
       {0.3726608323800677, -0.0827229736031544, 
 
        0.2796654088786644, 0.0886843035257766, 
 
        -0.05827395931928528, -0.0224161642982558}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {18.68541298455683, 18.5732478241865, 
 
       {0.3670107019213053, -0.01712558598051406, 
 
        0.3034864569962524, 0.05428682909255098, 
 
        -0.0812231904834133, -0.02981786929318137}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {18.79757814492715, 18.68541298455683, 
 
       {0.3689668028579187, 0.05241007033429934, 
 
        0.3141575277337347, 0.00974935517273917, 
 
        -0.1062456329044976, -0.03573757693703508}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {18.88310003705165, 18.79757814492715, 
 
       {0.3757462214429427, 0.1060568872069709, 
 
        0.3114512107964571, -0.03096492471291597, 
 
        -0.1259250434527079, -0.0398513161829418}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {18.96862192917615, 18.88310003705165, 
 
       {0.3870693636751939, 0.158366845927708, 
 
        0.2982589997177809, -0.07407760571381826, 
 
        -0.1357510645010563, -0.03310237404379157}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.05414382130065, 18.96862192917615, 
 
       {0.4027421912030583, 0.2074512134176328, 
 
        0.2736118383929473, -0.1200838196935024, 
 
        -0.1427557954794154, -0.02641388093007792}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.13966571342515, 19.05414382130065, 
 
       {0.4224048337182234, 0.2513421776618592, 
 
        0.2374746076124472, -0.1648370747241689, 
 
        -0.1389827055142412, -0.01231885569775835}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.22518760554965, 19.13966571342515, 
 
       {0.445529786832159, 0.2881070393457387, 
 
        0.190397772224642, -0.2056927945919754, 
 
        -0.1252338396782392, 0.005469829378313694}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.31070949767415, 19.22518760554965, 
 
       {0.4714305549212151, 0.315984969653511, 
 
        0.1338282440742222, -0.2390443166861731, 
 
        -0.1001203828650741, 0.02677385384012281}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.39623138979865, 19.31070949767415, 
 
       {0.4992824989873519, 0.3335295546507401, 
 
        0.07000767748865512, -0.2618620677160357, 
 
        -0.06493347412104102, 0.04897930786362809}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.48175328192315, 19.39623138979865, 
 
       {0.5281554878793904, 0.3397402952260724, 
 
        0.001881374221125575, -0.2717527574691589, 
 
        -0.02209055242472997, 0.06946426743136197}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.56727517404766, 19.48175328192315, 
 
       {0.5570566545951703, 0.3341639206539357, 
 
        -0.06714196216896483, -0.2674954295489713, 
 
        0.02448494335208126, 0.0852468161367972}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.65279706617216, 19.56727517404766, 
 
       {0.5849799346740756, 0.3169495831639415, 
 
        -0.1334921108095737, -0.2492160826062907, 
 
        0.07012893281391924, 0.0938449836145842}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.73831895829666, 19.65279706617216, 
 
       {0.6109579728009825, 0.2888480538220547, 
 
        -0.1937776557171189, -0.2184154742707526, 
 
        0.1101240243794568, 0.0937197186030686}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.82384085042116, 19.73831895829666, 
 
       {0.6341114852854414, 0.251153680156075, 
 
        -0.245103630749907, -0.1777464391827897, 
 
        0.1405335685355029, 0.0846779222392241}, 
 
       {0, 0, 0, 0, 0}}, 
 
      {19.91948351291989, 19.82384085042116, 
 
       {0.6557429257237063, 0.1997783549044253, 
 
        -0.2893333785348315, -0.1260535524222814, 
 
        0.1504410520531527, 0.03298659678422834, 
 
        -0.04177294005676014}, {0, 0, 0, 0, 0, 0}}, 
 
      {20., 19.91948351291989, 
 
       {0.6698916275074982, 0.1509924669492207, 
 
        -0.3146671120041085, -0.0819041403839457, 
 
        0.1385277101402602, -0.02424469133390998, 
 
        -0.06733763227177683}, {0, 0, 0, 0, 0, 0}}}]}}
;[o]
{{y -> InterpolatingFunction[{0., 20.}, <>]}}
:[font = input; preserveAspect; startGroup]
Plot[ Evaluate[ y[x] /. %], {x, 0, 20} ]
:[font = postscript; PostScript; formatAsPostScript; output; inactive; preserveAspect; pictureLeft = 100; pictureWidth = 299.125; pictureHeight = 184.812]
%!
%%Creator: Mathematica
%%AspectRatio: .61803 
MathPictureStart
%% Graphics
/Courier findfont 10  scalefont  setfont
% Scaling calculations
0.0238095 0.047619 -0.116445 0.719763 [
[(5)] .2619 .60332 0 2 Msboxa
[(10)] .5 .60332 0 2 Msboxa
[(15)] .7381 .60332 0 2 Msboxa
[(20)] .97619 .60332 0 2 Msboxa
[(0.2)] .01131 .02751 1 0 Msboxa
[(0.4)] .01131 .17146 1 0 Msboxa
[(0.6)] .01131 .31541 1 0 Msboxa
[(0.8)] .01131 .45937 1 0 Msboxa
[ -0.001 -0.001 0 0 ]
[ 1.001 .61903 0 0 ]
] MathScale
% Start of Graphics
1 setlinecap
1 setlinejoin
newpath
[ ] 0 setdash
0 g
p
p
.002 w
.2619 .60332 m
.2619 .60957 L
s
P
[(5)] .2619 .60332 0 2 Mshowa
p
.002 w
.5 .60332 m
.5 .60957 L
s
P
[(10)] .5 .60332 0 2 Mshowa
p
.002 w
.7381 .60332 m
.7381 .60957 L
s
P
[(15)] .7381 .60332 0 2 Mshowa
p
.002 w
.97619 .60332 m
.97619 .60957 L
s
P
[(20)] .97619 .60332 0 2 Mshowa
p
.001 w
.07143 .60332 m
.07143 .60707 L
s
P
p
.001 w
.11905 .60332 m
.11905 .60707 L
s
P
p
.001 w
.16667 .60332 m
.16667 .60707 L
s
P
p
.001 w
.21429 .60332 m
.21429 .60707 L
s
P
p
.001 w
.30952 .60332 m
.30952 .60707 L
s
P
p
.001 w
.35714 .60332 m
.35714 .60707 L
s
P
p
.001 w
.40476 .60332 m
.40476 .60707 L
s
P
p
.001 w
.45238 .60332 m
.45238 .60707 L
s
P
p
.001 w
.54762 .60332 m
.54762 .60707 L
s
P
p
.001 w
.59524 .60332 m
.59524 .60707 L
s
P
p
.001 w
.64286 .60332 m
.64286 .60707 L
s
P
p
.001 w
.69048 .60332 m
.69048 .60707 L
s
P
p
.001 w
.78571 .60332 m
.78571 .60707 L
s
P
p
.001 w
.83333 .60332 m
.83333 .60707 L
s
P
p
.001 w
.88095 .60332 m
.88095 .60707 L
s
P
p
.001 w
.92857 .60332 m
.92857 .60707 L
s
P
p
.002 w
0 .60332 m
1 .60332 L
s
P
p
.002 w
.02381 .02751 m
.03006 .02751 L
s
P
[(0.2)] .01131 .02751 1 0 Mshowa
p
.002 w
.02381 .17146 m
.03006 .17146 L
s
P
[(0.4)] .01131 .17146 1 0 Mshowa
p
.002 w
.02381 .31541 m
.03006 .31541 L
s
P
[(0.6)] .01131 .31541 1 0 Mshowa
p
.002 w
.02381 .45937 m
.03006 .45937 L
s
P
[(0.8)] .01131 .45937 1 0 Mshowa
p
.001 w
.02381 .0563 m
.02756 .0563 L
s
P
p
.001 w
.02381 .08509 m
.02756 .08509 L
s
P
p
.001 w
.02381 .11388 m
.02756 .11388 L
s
P
p
.001 w
.02381 .14267 m
.02756 .14267 L
s
P
p
.001 w
.02381 .20025 m
.02756 .20025 L
s
P
p
.001 w
.02381 .22904 m
.02756 .22904 L
s
P
p
.001 w
.02381 .25783 m
.02756 .25783 L
s
P
p
.001 w
.02381 .28662 m
.02756 .28662 L
s
P
p
.001 w
.02381 .3442 m
.02756 .3442 L
s
P
p
.001 w
.02381 .37299 m
.02756 .37299 L
s
P
p
.001 w
.02381 .40179 m
.02756 .40179 L
s
P
p
.001 w
.02381 .43058 m
.02756 .43058 L
s
P
p
.001 w
.02381 .48816 m
.02756 .48816 L
s
P
p
.001 w
.02381 .51695 m
.02756 .51695 L
s
P
p
.001 w
.02381 .54574 m
.02756 .54574 L
s
P
p
.001 w
.02381 .57453 m
.02756 .57453 L
s
P
p
.002 w
.02381 0 m
.02381 .61803 L
s
P
P
0 0 m
1 0 L
1 .61803 L
0 .61803 L
closepath
clip
newpath
p
p
p
.004 w
.02381 .60332 m
.02505 .60332 L
.02629 .60332 L
.02753 .60332 L
.02877 .60331 L
.03001 .6033 L
.03125 .60328 L
.03249 .60325 L
.03373 .60321 L
.03497 .60314 L
.03621 .60305 L
.03745 .60292 L
.03869 .60276 L
.03993 .60255 L
.04117 .60229 L
.04241 .60196 L
.04365 .60157 L
.04613 .60055 L
.04861 .59915 L
.05109 .5973 L
.05357 .59493 L
.05605 .59195 L
.05853 .58831 L
.06349 .57877 L
.06845 .56587 L
.07341 .5493 L
.08333 .50486 L
.09325 .44647 L
.10317 .37741 L
.12302 .2263 L
.13294 .15477 L
.14286 .093 L
.14782 .06744 L
.15278 .04629 L
.15774 .03014 L
.16022 .02412 L
.16146 .02164 L
.1627 .01954 L
.16394 .0178 L
.16518 .01645 L
.16642 .01548 L
.16766 .0149 L
.1689 .01472 L
.17014 .01492 L
.17138 .01553 L
.17262 .01653 L
.17386 .01793 L
.1751 .01972 L
.17758 .0245 L
.18006 .03082 L
Mistroke
.18254 .03865 L
.1875 .05857 L
.19246 .0835 L
.20238 .14413 L
.2123 .20995 L
.22222 .2693 L
.22718 .29343 L
.23214 .31271 L
.23462 .32037 L
.2371 .32667 L
.23958 .33159 L
.24082 .33354 L
.24206 .33515 L
.2433 .33643 L
.24454 .33737 L
.24578 .33799 L
.24702 .3383 L
.24826 .33828 L
.2495 .33797 L
.25074 .33736 L
.25198 .33646 L
.25446 .33385 L
.25694 .33021 L
.2619 .3202 L
.27183 .29202 L
.28175 .25833 L
.29167 .22559 L
.29663 .21149 L
.30159 .19985 L
.30407 .19516 L
.30655 .19131 L
.30779 .18972 L
.30903 .18837 L
.31027 .18727 L
.31151 .18641 L
.31275 .18581 L
.31399 .18547 L
.31523 .1854 L
.31647 .18559 L
.31771 .18606 L
.31895 .18681 L
.32019 .18784 L
.32143 .18914 L
.34127 .24551 L
.36111 .33749 L
.36607 .35782 L
.37103 .3748 L
.37599 .38758 L
.37847 .3922 L
.37971 .39404 L
Mistroke
.38095 .39556 L
.38219 .39677 L
.38343 .39765 L
.38467 .39822 L
.38591 .39845 L
.38715 .39837 L
.38839 .39797 L
.38963 .39725 L
.39087 .39622 L
.39211 .39489 L
.39335 .39325 L
.39583 .38909 L
.40079 .37749 L
.40575 .36198 L
.41071 .34325 L
.42063 .29911 L
.43056 .25129 L
.44048 .206 L
.4504 .16912 L
.45288 .16186 L
.45536 .15554 L
.45784 .15022 L
.46032 .14598 L
.46156 .14427 L
.4628 .14286 L
.46404 .14173 L
.46528 .14091 L
.46652 .14039 L
.46776 .14017 L
.469 .14026 L
.47024 .14067 L
.47148 .14138 L
.47272 .14241 L
.47396 .14375 L
.4752 .1454 L
.48016 .15507 L
.48264 .16166 L
.48512 .16935 L
.49008 .18767 L
.5 .23291 L
.50992 .28187 L
.51488 .30456 L
.51984 .32445 L
.5248 .3406 L
.52728 .34704 L
.52976 .35229 L
.531 .35446 L
.53224 .35632 L
.53348 .35787 L
.53472 .3591 L
Mistroke
.53596 .36002 L
.5372 .36063 L
.53844 .36092 L
.53968 .3609 L
.57937 .24439 L
.58929 .20435 L
.59425 .18688 L
.59921 .17204 L
.60169 .16581 L
.60417 .16048 L
.60665 .15612 L
.60913 .1528 L
.61037 .15154 L
.61161 .15056 L
.61285 .14986 L
.61409 .14946 L
.61533 .14934 L
.61657 .14953 L
.61781 .15002 L
.61905 .15081 L
.62029 .1519 L
.62153 .1533 L
.62401 .15701 L
.62649 .16193 L
.62897 .16802 L
.63393 .18356 L
.63889 .20305 L
.64881 .25042 L
.65873 .30124 L
.66369 .32474 L
.66865 .34534 L
.67361 .36208 L
.67609 .36875 L
.67857 .37421 L
.68105 .37839 L
.68229 .38 L
.68353 .38129 L
.68477 .38224 L
.68601 .38287 L
.68725 .38318 L
.68849 .38317 L
.68973 .38283 L
.69097 .38218 L
.69221 .38122 L
.69345 .37996 L
.69593 .37655 L
.69841 .37201 L
.70337 .35984 L
.70833 .34409 L
.71825 .30478 L
Mistroke
.72817 .26029 L
.7381 .2169 L
.74306 .19751 L
.74802 .18062 L
.75298 .16689 L
.75546 .16139 L
.75794 .1569 L
.76042 .15348 L
.76166 .15219 L
.7629 .15118 L
.76414 .15046 L
.76538 .15004 L
.76662 .14991 L
.76786 .15009 L
.7691 .15057 L
.77034 .15136 L
.77158 .15245 L
.77282 .15385 L
.7753 .15756 L
.77778 .16246 L
.78274 .17569 L
.7877 .19305 L
.79762 .23693 L
.80754 .2856 L
.8125 .30858 L
.81746 .32903 L
.82242 .34593 L
.8249 .3528 L
.82738 .35851 L
.82986 .363 L
.8311 .36479 L
.83234 .36626 L
.83358 .36741 L
.83482 .36825 L
.83606 .36877 L
.8373 .36897 L
.83854 .36887 L
.83978 .36845 L
.84102 .36774 L
.84226 .36672 L
.84474 .36381 L
.84722 .35978 L
.85218 .34864 L
.85714 .33391 L
.87698 .25373 L
.8869 .21177 L
.89187 .19299 L
.89683 .17665 L
.90179 .1634 L
.90427 .15813 L
Mistroke
.90675 .15386 L
.90923 .15065 L
.91047 .14945 L
.91171 .14854 L
.91295 .14792 L
.91419 .1476 L
.91543 .14757 L
.91667 .14784 L
.91791 .14842 L
.91915 .1493 L
.92039 .15049 L
.92163 .15198 L
.92411 .15589 L
.92659 .161 L
.93155 .1747 L
.93651 .19259 L
.97619 .36572 L
Mfstroke
P
P
P
% End of Graphics
MathPictureEnd

:[font = output; output; inactive; preserveAspect; endGroup; endGroup]
Graphics["<<>>"]
;[o]
-Graphics-
:[font = subsection; inactive; preserveAspect; startGroup]
Solving Equations
:[font = text; inactive; preserveAspect]
This is how you solve a quadratic equation in Mathematica.
:[font = input; preserveAspect; startGroup]
Solve[x^2 + 2 a x + 1 == 0, x]
:[font = output; output; inactive; preserveAspect; endGroup]
{{x -> (-2*a + (-4 + 4*a^2)^(1/2))/2}, 
   {x -> (-2*a - (-4 + 4*a^2)^(1/2))/2}}























































































;[o]
                           2
       -2 a + Sqrt[-4 + 4 a ]
{{x -> ----------------------}, 
                 2
 
                             2
         -2 a - Sqrt[-4 + 4 a ]
   {x -> ----------------------}}
                   2
:[font = text; inactive; preserveAspect]
Here's a more complicated example.
:[font = input; preserveAspect; startGroup]
Solve[x^5 + 3x + 1 == 0, x]
:[font = output; output; inactive; preserveAspect; endGroup]
{ToRules[Roots[3*x + x^5 == -1, x]]}























































































;[o]
                      5
{ToRules[Roots[3 x + x  == -1, x]]}
:[font = text; inactive; preserveAspect]
It is a fact of mathematics that there is no way to get an exact formula for the solutions of a quintic equation like this.  You can nevertheless ask Mathematica to give you numerical results.  You get the five complex number roots to the equation.
:[font = input; preserveAspect; startGroup]
N[%]
:[font = output; output; inactive; preserveAspect; endGroup]
{{x -> -0.839072 - 0.943852*I}, 
   {x -> -0.839072 + 0.943852*I}, {x -> -0.331989}, 
   {x -> 1.00507 - 0.937259*I}, {x -> 1.00507 + 0.937259*I}}























































































;[o]
{{x -> -0.839072 - 0.943852 I}, 
 
   {x -> -0.839072 + 0.943852 I}, {x -> -0.331989}, 
 
   {x -> 1.00507 - 0.937259 I}, {x -> 1.00507 + 0.937259 I}}
:[font = text; inactive; preserveAspect]
When equations contain complicated functions there is in general no systematic procedure for finding all solutions, even numerically.  The Mathematica function FindRoot searches for a numerical solution to an equation, starting at a specified point.  
:[font = input; preserveAspect; startGroup]
FindRoot[{Sin[x] == x - y, Cos[y] == x + y},
                         {x, 1}, {y, 0}]
:[font = output; output; inactive; preserveAspect; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
{x -> 0.883402, y -> 0.1105}
:[font = text; inactive; preserveAspect]
Mathematica also has an efficient routine for finding the solution to linear equations. 
Here's a simple example.
:[font = input; preserveAspect; startGroup]
LinearSolve[{{1.02, 5.9}, {2.87, 1.9}}, {1.9, 1.06}]
:[font = output; output; inactive; preserveAspect; endGroup; endGroup; endGroup]
The Unformatted text for this cell was not generated.
Use options in the Actions Settings dialog box to control
when Unformatted text is generated.























































































;[o]
{0.176325, 0.291551}
:[font = section; inactive; preserveAspect; startGroup]
Graphics
:[font = text; inactive; preserveAspect]
Here is a simple Mathematica  plot. 
:[font = input; preserveAspect; startGroup]
Plot[Sin[x], {x, 0, 2Pi}]
:[font = postscript; PostScript; formatAsPostScript; output; inactive; preserveAspect; pictureLeft = 100; pictureWidth = 299.125; pictureHeight = 184.812]
%!
%%Creator: Mathematica
%%AspectRatio: .61803 
MathPictureStart
%% Graphics
/Courier findfont 10  scalefont  setfont
% Scaling calculations
0.0238095 0.151576 0.309017 0.294302 [
[(1)] .17539 .30902 0 2 Msboxa
[(2)] .32696 .30902 0 2 Msboxa
[(3)] .47854 .30902 0 2 Msboxa
[(4)] .63011 .30902 0 2 Msboxa
[(5)] .78169 .30902 0 2 Msboxa
[(6)] .93327 .30902 0 2 Msboxa
[(-1)] .01131 .01472 1 0 Msboxa
[(-0.5)] .01131 .16187 1 0 Msboxa
[(0.5)] .01131 .45617 1 0 Msboxa
[(1)] .01131 .60332 1 0 Msboxa
[ -0.001 -0.001 0 0 ]
[ 1.001 .61903 0 0 ]
] MathScale
% Start of Graphics
1 setlinecap
1 setlinejoin
newpath
[ ] 0 setdash
0 g
p
p
.002 w
.17539 .30902 m
.17539 .31527 L
s
P
[(1)] .17539 .30902 0 2 Mshowa
p
.002 w
.32696 .30902 m
.32696 .31527 L
s
P
[(2)] .32696 .30902 0 2 Mshowa
p
.002 w
.47854 .30902 m
.47854 .31527 L
s
P
[(3)] .47854 .30902 0 2 Mshowa
p
.002 w
.63011 .30902 m
.63011 .31527 L
s
P
[(4)] .63011 .30902 0 2 Mshowa
p
.002 w
.78169 .30902 m
.78169 .31527 L
s
P
[(5)] .78169 .30902 0 2 Mshowa
p
.002 w
.93327 .30902 m
.93327 .31527 L
s
P
[(6)] .93327 .30902 0 2 Mshowa
p
.001 w
.05412 .30902 m
.05412 .31277 L
s
P
p
.001 w
.08444 .30902 m
.08444 .31277 L
s
P
p
.001 w
.11476 .30902 m
.11476 .31277 L
s
P
p
.001 w
.14507 .30902 m
.14507 .31277 L
s
P
p
.001 w
.2057 .30902 m
.2057 .31277 L
s
P
p
.001 w
.23602 .30902 m
.23602 .31277 L
s
P
p
.001 w
.26633 .30902 m
.26633 .31277 L
s
P
p
.001 w
.29665 .30902 m
.29665 .31277 L
s
P
p
.001 w
.35728 .30902 m
.35728 .31277 L
s
P
p
.001 w
.38759 .30902 m
.38759 .31277 L
s
P
p
.001 w
.41791 .30902 m
.41791 .31277 L
s
P
p
.001 w
.44822 .30902 m
.44822 .31277 L
s
P
p
.001 w
.50885 .30902 m
.50885 .31277 L
s
P
p
.001 w
.53917 .30902 m
.53917 .31277 L
s
P
p
.001 w
.56948 .30902 m
.56948 .31277 L
s
P
p
.001 w
.5998 .30902 m
.5998 .31277 L
s
P
p
.001 w
.66043 .30902 m
.66043 .31277 L
s
P
p
.001 w
.69074 .30902 m
.69074 .31277 L
s
P
p
.001 w
.72106 .30902 m
.72106 .31277 L
s
P
p
.001 w
.75137 .30902 m
.75137 .31277 L
s
P
p
.001 w
.81201 .30902 m
.81201 .31277 L
s
P
p
.001 w
.84232 .30902 m
.84232 .31277 L
s
P
p
.001 w
.87264 .30902 m
.87264 .31277 L
s
P
p
.001 w
.90295 .30902 m
.90295 .31277 L
s
P
p
.001 w
.96358 .30902 m
.96358 .31277 L
s
P
p
.001 w
.9939 .30902 m
.9939 .31277 L
s
P
p
.002 w
0 .30902 m
1 .30902 L
s
P
p
.002 w
.02381 .01472 m
.03006 .01472 L
s
P
[(-1)] .01131 .01472 1 0 Mshowa
p
.002 w
.02381 .16187 m
.03006 .16187 L
s
P
[(-0.5)] .01131 .16187 1 0 Mshowa
p
.002 w
.02381 .45617 m
.03006 .45617 L
s
P
[(0.5)] .01131 .45617 1 0 Mshowa
p
.002 w
.02381 .60332 m
.03006 .60332 L
s
P
[(1)] .01131 .60332 1 0 Mshowa
p
.001 w
.02381 .04415 m
.02756 .04415 L
s
P
p
.001 w
.02381 .07358 m
.02756 .07358 L
s
P
p
.001 w
.02381 .10301 m
.02756 .10301 L
s
P
p
.001 w
.02381 .13244 m
.02756 .13244 L
s
P
p
.001 w
.02381 .1913 m
.02756 .1913 L
s
P
p
.001 w
.02381 .22073 m
.02756 .22073 L
s
P
p
.001 w
.02381 .25016 m
.02756 .25016 L
s
P
p
.001 w
.02381 .27959 m
.02756 .27959 L
s
P
p
.001 w
.02381 .33845 m
.02756 .33845 L
s
P
p
.001 w
.02381 .36788 m
.02756 .36788 L
s
P
p
.001 w
.02381 .39731 m
.02756 .39731 L
s
P
p
.001 w
.02381 .42674 m
.02756 .42674 L
s
P
p
.001 w
.02381 .4856 m
.02756 .4856 L
s
P
p
.001 w
.02381 .51503 m
.02756 .51503 L
s
P
p
.001 w
.02381 .54446 m
.02756 .54446 L
s
P
p
.001 w
.02381 .57389 m
.02756 .57389 L
s
P
p
.002 w
.02381 0 m
.02381 .61803 L
s
P
P
0 0 m
1 0 L
1 .61803 L
0 .61803 L
closepath
clip
newpath
p
p
.004 w
.02381 .30902 m
.06349 .38519 L
.10317 .45617 L
.14286 .51712 L
.1627 .5425 L
.18254 .56389 L
.20238 .58092 L
.2123 .5877 L
.22222 .59329 L
.23214 .59766 L
.2371 .59939 L
.24206 .6008 L
.24702 .6019 L
.2495 .60233 L
.25198 .60269 L
.25446 .60296 L
.2557 .60307 L
.25694 .60316 L
.25818 .60323 L
.25942 .60328 L
.26066 .60331 L
.2619 .60332 L
.26314 .60331 L
.26438 .60328 L
.26562 .60323 L
.26687 .60316 L
.26935 .60296 L
.27183 .60269 L
.27431 .60233 L
.27679 .6019 L
.28175 .6008 L
.28671 .59939 L
.29167 .59766 L
.30159 .59329 L
.32143 .58092 L
.34127 .56389 L
.38095 .51712 L
.42063 .45617 L
.46032 .38519 L
.5 .30902 L
.53968 .23285 L
.57937 .16187 L
.61905 .10091 L
.63889 .07553 L
.65873 .05414 L
.67857 .03712 L
.68849 .03033 L
.69841 .02474 L
.70833 .02037 L
.71329 .01865 L
Mistroke
.71825 .01723 L
.72321 .01613 L
.72569 .0157 L
.72817 .01535 L
.73065 .01507 L
.73189 .01496 L
.73313 .01487 L
.73437 .0148 L
.73562 .01475 L
.73686 .01472 L
.7381 .01472 L
.73934 .01472 L
.74058 .01475 L
.74182 .0148 L
.74306 .01487 L
.74554 .01507 L
.74802 .01535 L
.7505 .0157 L
.75298 .01613 L
.75794 .01723 L
.7629 .01865 L
.76786 .02037 L
.77778 .02474 L
.79762 .03712 L
.81746 .05414 L
.85714 .10091 L
.89683 .16187 L
.93651 .23285 L
.97619 .30902 L
Mfstroke
P
P
% End of Graphics
MathPictureEnd

:[font = output; output; inactive; preserveAspect; endGroup]
Graphics["<<>>"]
;[o]
-Graphics-
:[font = text; inactive; preserveAspect]
There are many options you can specify for a plot.  Using Show you can redraw the previous plot 
with specified options.
:[font = input; preserveAspect; startGroup]
Show[% , Frame -> True, 
            PlotLabel  -> "The Sine Function"]
:[font = postscript; PostScript; formatAsPostScript; output; inactive; preserveAspect; pictureLeft = 100; pictureWidth = 299.125; pictureHeight = 184.812]
%!
%%Creator: Mathematica
%%AspectRatio: .61803 
MathPictureStart
%% Graphics
/Courier findfont 10  scalefont  setfont
% Scaling calculations
0.0238095 0.151576 0.309017 0.294302 [
[(0)] .02381 0 0 2 Msboxa
[(1)] .17539 0 0 2 Msboxa
[(2)] .32696 0 0 2 Msboxa
[(3)] .47854 0 0 2 Msboxa
[(4)] .63011 0 0 2 Msboxa
[(5)] .78169 0 0 2 Msboxa
[(6)] .93327 0 0 2 Msboxa
[(-1)] -0.0125 .01472 1 0 Msboxa
[(-0.5)] -0.0125 .16187 1 0 Msboxa
[(0)] -0.0125 .30902 1 0 Msboxa
[(0.5)] -0.0125 .45617 1 0 Msboxa
[(1)] -0.0125 .60332 1 0 Msboxa
[(The Sine Function)] .5 .61803 0 -2 Msboxa
[ -0.001 -0.001 0 0 ]
[ 1.001 .61903 0 0 ]
] MathScale
% Start of Graphics
1 setlinecap
1 setlinejoin
newpath
[ ] 0 setdash
0 g
p
p
.002 w
.02381 0 m
.02381 .00625 L
s
P
[(0)] .02381 0 0 2 Mshowa
p
.002 w
.17539 0 m
.17539 .00625 L
s
P
[(1)] .17539 0 0 2 Mshowa
p
.002 w
.32696 0 m
.32696 .00625 L
s
P
[(2)] .32696 0 0 2 Mshowa
p
.002 w
.47854 0 m
.47854 .00625 L
s
P
[(3)] .47854 0 0 2 Mshowa
p
.002 w
.63011 0 m
.63011 .00625 L
s
P
[(4)] .63011 0 0 2 Mshowa
p
.002 w
.78169 0 m
.78169 .00625 L
s
P
[(5)] .78169 0 0 2 Mshowa
p
.002 w
.93327 0 m
.93327 .00625 L
s
P
[(6)] .93327 0 0 2 Mshowa
p
.001 w
.05412 0 m
.05412 .00375 L
s
P
p
.001 w
.08444 0 m
.08444 .00375 L
s
P
p
.001 w
.11476 0 m
.11476 .00375 L
s
P
p
.001 w
.14507 0 m
.14507 .00375 L
s
P
p
.001 w
.2057 0 m
.2057 .00375 L
s
P
p
.001 w
.23602 0 m
.23602 .00375 L
s
P
p
.001 w
.26633 0 m
.26633 .00375 L
s
P
p
.001 w
.29665 0 m
.29665 .00375 L
s
P
p
.001 w
.35728 0 m
.35728 .00375 L
s
P
p
.001 w
.38759 0 m
.38759 .00375 L
s
P
p
.001 w
.41791 0 m
.41791 .00375 L
s
P
p
.001 w
.44822 0 m
.44822 .00375 L
s
P
p
.001 w
.50885 0 m
.50885 .00375 L
s
P
p
.001 w
.53917 0 m
.53917 .00375 L
s
P
p
.001 w
.56948 0 m
.56948 .00375 L
s
P
p
.001 w
.5998 0 m
.5998 .00375 L
s
P
p
.001 w
.66043 0 m
.66043 .00375 L
s
P
p
.001 w
.69074 0 m
.69074 .00375 L
s
P
p
.001 w
.72106 0 m
.72106 .00375 L
s
P
p
.001 w
.75137 0 m
.75137 .00375 L
s
P
p
.001 w
.81201 0 m
.81201 .00375 L
s
P
p
.001 w
.84232 0 m
.84232 .00375 L
s
P
p
.001 w
.87264 0 m
.87264 .00375 L
s
P
p
.001 w
.90295 0 m
.90295 .00375 L
s
P
p
.001 w
.96358 0 m
.96358 .00375 L
s
P
p
.001 w
.9939 0 m
.9939 .00375 L
s
P
p
.002 w
0 0 m
1 0 L
s
P
p
.002 w
0 .01472 m
.00625 .01472 L
s
P
[(-1)] -0.0125 .01472 1 0 Mshowa
p
.002 w
0 .16187 m
.00625 .16187 L
s
P
[(-0.5)] -0.0125 .16187 1 0 Mshowa
p
.002 w
0 .30902 m
.00625 .30902 L
s
P
[(0)] -0.0125 .30902 1 0 Mshowa
p
.002 w
0 .45617 m
.00625 .45617 L
s
P
[(0.5)] -0.0125 .45617 1 0 Mshowa
p
.002 w
0 .60332 m
.00625 .60332 L
s
P
[(1)] -0.0125 .60332 1 0 Mshowa
p
.001 w
0 .04415 m
.00375 .04415 L
s
P
p
.001 w
0 .07358 m
.00375 .07358 L
s
P
p
.001 w
0 .10301 m
.00375 .10301 L
s
P
p
.001 w
0 .13244 m
.00375 .13244 L
s
P
p
.001 w
0 .1913 m
.00375 .1913 L
s
P
p
.001 w
0 .22073 m
.00375 .22073 L
s
P
p
.001 w
0 .25016 m
.00375 .25016 L
s
P
p
.001 w
0 .27959 m
.00375 .27959 L
s
P
p
.001 w
0 .33845 m
.00375 .33845 L
s
P
p
.001 w
0 .36788 m
.00375 .36788 L
s
P
p
.001 w
0 .39731 m
.00375 .39731 L
s
P
p
.001 w
0 .42674 m
.00375 .42674 L
s
P
p
.001 w
0 .4856 m
.00375 .4856 L
s
P
p
.001 w
0 .51503 m
.00375 .51503 L
s
P
p
.001 w
0 .54446 m
.00375 .54446 L
s
P
p
.001 w
0 .57389 m
.00375 .57389 L
s
P
p
.002 w
0 0 m
0 .61803 L
s
P
P
p
p
.002 w
.02381 .61178 m
.02381 .61803 L
s
P
p
.002 w
.17539 .61178 m
.17539 .61803 L
s
P
p
.002 w
.32696 .61178 m
.32696 .61803 L
s
P
p
.002 w
.47854 .61178 m
.47854 .61803 L
s
P
p
.002 w
.63011 .61178 m
.63011 .61803 L
s
P
p
.002 w
.78169 .61178 m
.78169 .61803 L
s
P
p
.002 w
.93327 .61178 m
.93327 .61803 L
s
P
p
.001 w
.05412 .61428 m
.05412 .61803 L
s
P
p
.001 w
.08444 .61428 m
.08444 .61803 L
s
P
p
.001 w
.11476 .61428 m
.11476 .61803 L
s
P
p
.001 w
.14507 .61428 m
.14507 .61803 L
s
P
p
.001 w
.2057 .61428 m
.2057 .61803 L
s
P
p
.001 w
.23602 .61428 m
.23602 .61803 L
s
P
p
.001 w
.26633 .61428 m
.26633 .61803 L
s
P
p
.001 w
.29665 .61428 m
.29665 .61803 L
s
P
p
.001 w
.35728 .61428 m
.35728 .61803 L
s
P
p
.001 w
.38759 .61428 m
.38759 .61803 L
s
P
p
.001 w
.41791 .61428 m
.41791 .61803 L
s
P
p
.001 w
.44822 .61428 m
.44822 .61803 L
s
P
p
.001 w
.50885 .61428 m
.50885 .61803 L
s
P
p
.001 w
.53917 .61428 m
.53917 .61803 L
s
P
p
.001 w
.56948 .61428 m
.56948 .61803 L
s
P
p
.001 w
.5998 .61428 m
.5998 .61803 L
s
P
p
.001 w
.66043 .61428 m
.66043 .61803 L
s
P
p
.001 w
.69074 .61428 m
.69074 .61803 L
s
P
p
.001 w
.72106 .61428 m
.72106 .61803 L
s
P
p
.001 w
.75137 .61428 m
.75137 .61803 L
s
P
p
.001 w
.81201 .61428 m
.81201 .61803 L
s
P
p
.001 w
.84232 .61428 m
.84232 .61803 L
s
P
p
.001 w
.87264 .61428 m
.87264 .61803 L
s
P
p
.001 w
.90295 .61428 m
.90295 .61803 L
s
P
p
.001 w
.96358 .61428 m
.96358 .61803 L
s
P
p
.001 w
.9939 .61428 m
.9939 .61803 L
s
P
p
.002 w
0 .61803 m
1 .61803 L
s
P
[(The Sine Function)] .5 .61803 0 -2 Mshowa
p
.002 w
.99375 .01472 m
1 .01472 L
s
P
p
.002 w
.99375 .16187 m
1 .16187 L
s
P
p
.002 w
.99375 .30902 m
1 .30902 L
s
P
p
.002 w
.99375 .45617 m
1 .45617 L
s
P
p
.002 w
.99375 .60332 m
1 .60332 L
s
P
p
.001 w
.99625 .04415 m
1 .04415 L
s
P
p
.001 w
.99625 .07358 m
1 .07358 L
s
P
p
.001 w
.99625 .10301 m
1 .10301 L
s
P
p
.001 w
.99625 .13244 m
1 .13244 L
s
P
p
.001 w
.99625 .1913 m
1 .1913 L
s
P
p
.001 w
.99625 .22073 m
1 .22073 L
s
P
p
.001 w
.99625 .25016 m
1 .25016 L
s
P
p
.001 w
.99625 .27959 m
1 .27959 L
s
P
p
.001 w
.99625 .33845 m
1 .33845 L
s
P
p
.001 w
.99625 .36788 m
1 .36788 L
s
P
p
.001 w
.99625 .39731 m
1 .39731 L
s
P
p
.001 w
.99625 .42674 m
1 .42674 L
s
P
p
.001 w
.99625 .4856 m
1 .4856 L
s
P
p
.001 w
.99625 .51503 m
1 .51503 L
s
P
p
.001 w
.99625 .54446 m
1 .54446 L
s
P
p
.001 w
.99625 .57389 m
1 .57389 L
s
P
p
.002 w
1 0 m
1 .61803 L
s
P
P
p
p
.002 w
0 .30902 m
1 .30902 L
s
P
P
0 0 m
1 0 L
1 .61803 L
0 .61803 L
closepath
clip
newpath
p
p
.004 w
.02381 .30902 m
.06349 .38519 L
.10317 .45617 L
.14286 .51712 L
.1627 .5425 L
.18254 .56389 L
.20238 .58092 L
.2123 .5877 L
.22222 .59329 L
.23214 .59766 L
.2371 .59939 L
.24206 .6008 L
.24702 .6019 L
.2495 .60233 L
.25198 .60269 L
.25446 .60296 L
.2557 .60307 L
.25694 .60316 L
.25818 .60323 L
.25942 .60328 L
.26066 .60331 L
.2619 .60332 L
.26314 .60331 L
.26438 .60328 L
.26562 .60323 L
.26687 .60316 L
.26935 .60296 L
.27183 .60269 L
.27431 .60233 L
.27679 .6019 L
.28175 .6008 L
.28671 .59939 L
.29167 .59766 L
.30159 .59329 L
.32143 .58092 L
.34127 .56389 L
.38095 .51712 L
.42063 .45617 L
.46032 .38519 L
.5 .30902 L
.53968 .23285 L
.57937 .16187 L
.61905 .10091 L
.63889 .07553 L
.65873 .05414 L
.67857 .03712 L
.68849 .03033 L
.69841 .02474 L
.70833 .02037 L
.71329 .01865 L
Mistroke
.71825 .01723 L
.72321 .01613 L
.72569 .0157 L
.72817 .01535 L
.73065 .01507 L
.73189 .01496 L
.73313 .01487 L
.73437 .0148 L
.73562 .01475 L
.73686 .01472 L
.7381 .01472 L
.73934 .01472 L
.74058 .01475 L
.74182 .0148 L
.74306 .01487 L
.74554 .01507 L
.74802 .01535 L
.7505 .0157 L
.75298 .01613 L
.75794 .01723 L
.7629 .01865 L
.76786 .02037 L
.77778 .02474 L
.79762 .03712 L
.81746 .05414 L
.85714 .10091 L
.89683 .16187 L
.93651 .23285 L
.97619 .30902 L
Mfstroke
P
P
% End of Graphics
MathPictureEnd

:[font = output; output; inactive; preserveAspect; endGroup]
Graphics["<<>>"]
;[o]
-Graphics-
:[font = text; inactive; preserveAspect]
Now for some three-dimensional graphics.
:[font = input; preserveAspect; startGroup]
Plot3D[Sin[x] Sin[3 y], {x, -2, 2}, {y, -2, 2}]
:[font = postscript; PostScript; formatAsPostScript; output; inactive; preserveAspect; pictureLeft = 100; pictureWidth = 299.875; pictureHeight = 246]
%!
%%Creator: Mathematica
%%AspectRatio: .82055 
MathPictureStart
%% SurfaceGraphics
/Courier findfont 10  scalefont  setfont
% Scaling calculations
0.0249355 0.99742 -0.0396341 0.99742 [
[(-2)] .05113 .25884 1 .93395 Msboxa
[(-1)] .1926 .20316 .96648 1 Msboxa
[(0)] .34275 .1441 .86223 1 Msboxa
[(1)] .50241 .08133 .75799 1 Msboxa
[(2)] .6725 .0145 .65374 1 Msboxa
[(-2)] .69093 .02039 -1 .39157 Msboxa
[(-1)] .76901 .13994 -1 .36413 Msboxa
[(0)] .83872 .24668 -1 .34028 Msboxa
[(1)] .90133 .34257 -1 .31937 Msboxa
[(2)] .95788 .42917 -1 .30087 Msboxa
[(-1)] .04817 .27478 1 -0.391 Msboxa
[(-0.5)] .04 .32597 1 -0.38088 Msboxa
[(0)] .03155 .37899 1 -0.37036 Msboxa
[(0.5)] .02278 .43393 1 -0.35942 Msboxa
[(1)] .01369 .4909 1 -0.34803 Msboxa
[ 0 0 0 0 ]
[ 1 .82055 0 0 ]
] MathScale
% Start of Graphics
1 setlinecap
1 setlinejoin
newpath
[ ] 0 setdash
0 g
p
p
.002 w
.06024 .26735 m
.67932 .02494 L
s
P
p
.002 w
.06024 .26735 m
.0648 .2716 L
s
P
[(-2)] .05113 .25884 1 .93395 Mshowa
p
.002 w
.20126 .21213 m
.2056 .21661 L
s
P
[(-1)] .1926 .20316 .96648 1 Mshowa
p
.002 w
.35089 .15354 m
.35496 .15826 L
s
P
[(0)] .34275 .1441 .86223 1 Mshowa
p
.002 w
.50994 .09126 m
.5137 .09623 L
s
P
[(1)] .50241 .08133 .75799 1 Mshowa
p
.002 w
.67932 .02494 m
.68274 .03015 L
s
P
[(2)] .6725 .0145 .65374 1 Mshowa
p
.001 w
.08779 .25656 m
.0905 .25914 L
s
P
p
.001 w
.11567 .24565 m
.11835 .24825 L
s
P
p
.001 w
.14386 .2346 m
.14652 .23724 L
s
P
p
.001 w
.17239 .22343 m
.17502 .22609 L
s
P
p
.001 w
.23048 .20069 m
.23305 .20341 L
s
P
p
.001 w
.26004 .18911 m
.26258 .19186 L
s
P
p
.001 w
.28996 .1774 m
.29247 .18017 L
s
P
p
.001 w
.32024 .16554 m
.32272 .16835 L
s
P
p
.001 w
.38192 .14139 m
.38433 .14425 L
s
P
p
.001 w
.41333 .12909 m
.4157 .13198 L
s
P
p
.001 w
.44513 .11664 m
.44747 .11956 L
s
P
p
.001 w
.47733 .10403 m
.47963 .10698 L
s
P
p
.001 w
.54296 .07833 m
.54518 .08134 L
s
P
p
.001 w
.5764 .06524 m
.57857 .06828 L
s
P
p
.001 w
.61027 .05198 m
.6124 .05505 L
s
P
p
.001 w
.64457 .03854 m
.64666 .04164 L
s
P
P
p
p
.002 w
.67932 .02494 m
.94594 .43277 L
s
P
p
.002 w
.67932 .02494 m
.67352 .02721 L
s
P
[(-2)] .69093 .02039 -1 .39157 Mshowa
p
.002 w
.7573 .14421 m
.75144 .14634 L
s
P
[(-1)] .76901 .13994 -1 .36413 Mshowa
p
.002 w
.82692 .2507 m
.82101 .25271 L
s
P
[(0)] .83872 .24668 -1 .34028 Mshowa
p
.002 w
.88945 .34636 m
.88352 .34826 L
s
P
[(1)] .90133 .34257 -1 .31937 Mshowa
p
.002 w
.94594 .43277 m
.93997 .43456 L
s
P
[(2)] .95788 .42917 -1 .30087 Mshowa
p
.001 w
.69566 .04992 m
.69217 .05127 L
s
P
p
.001 w
.71161 .07432 m
.70811 .07565 L
s
P
p
.001 w
.72719 .09816 m
.72369 .09947 L
s
P
p
.001 w
.74242 .12145 m
.73891 .12274 L
s
P
p
.001 w
.77184 .16646 m
.76832 .16772 L
s
P
p
.001 w
.78607 .18822 m
.78254 .18947 L
s
P
p
.001 w
.79998 .2095 m
.79645 .21074 L
s
P
p
.001 w
.81359 .23032 m
.81006 .23154 L
s
P
p
.001 w
.83995 .27064 m
.83641 .27183 L
s
P
p
.001 w
.85272 .29017 m
.84917 .29135 L
s
P
p
.001 w
.86522 .30929 m
.86166 .31045 L
s
P
p
.001 w
.87746 .32802 m
.8739 .32917 L
s
P
p
.001 w
.90121 .36434 m
.89764 .36546 L
s
P
p
.001 w
.91272 .38196 m
.90915 .38307 L
s
P
p
.001 w
.92402 .39923 m
.92044 .40033 L
s
P
p
.001 w
.93508 .41616 m
.93151 .41725 L
s
P
P
p
p
.002 w
.06024 .26735 m
.02494 .49015 L
s
P
p
.002 w
.05978 .27024 m
.06559 .26797 L
s
P
[(-1)] .04817 .27478 1 -0.391 Mshowa
p
.002 w
.05166 .32154 m
.05748 .31932 L
s
P
[(-0.5)] .04 .32597 1 -0.38088 Mshowa
p
.002 w
.04324 .37466 m
.04908 .37249 L
s
P
[(0)] .03155 .37899 1 -0.37036 Mshowa
p
.002 w
.03451 .42971 m
.04038 .4276 L
s
P
[(0.5)] .02278 .43393 1 -0.35942 Mshowa
p
.002 w
.02547 .4868 m
.03135 .48475 L
s
P
[(1)] .01369 .4909 1 -0.34803 Mshowa
p
.001 w
.05818 .28036 m
.06167 .279 L
s
P
p
.001 w
.05657 .29055 m
.06005 .2892 L
s
P
p
.001 w
.05494 .3008 m
.05843 .29946 L
s
P
p
.001 w
.0533 .31113 m
.0568 .3098 L
s
P
p
.001 w
.05 .33201 m
.05349 .33069 L
s
P
p
.001 w
.04832 .34256 m
.05182 .34124 L
s
P
p
.001 w
.04664 .35318 m
.05014 .35187 L
s
P
p
.001 w
.04494 .36388 m
.04845 .36258 L
s
P
p
.001 w
.04152 .38551 m
.04503 .38422 L
s
P
p
.001 w
.03978 .39644 m
.0433 .39516 L
s
P
p
.001 w
.03804 .40745 m
.04155 .40617 L
s
P
p
.001 w
.03628 .41854 m
.0398 .41727 L
s
P
p
.001 w
.03273 .44096 m
.03625 .43971 L
s
P
p
.001 w
.03093 .4523 m
.03446 .45105 L
s
P
p
.001 w
.02912 .46372 m
.03265 .46247 L
s
P
p
.001 w
.0273 .47522 m
.03083 .47398 L
s
P
P
0 0 m
1 0 L
1 .82055 L
0 .82055 L
closepath
clip
newpath
p
.002 w
.06024 .26735 m
.02494 .49015 L
s
.02494 .49015 m
.40296 .79562 L
s
.40296 .79562 m
.41001 .59401 L
s
.41001 .59401 m
.06024 .26735 L
s
.67932 .02494 m
.94594 .43277 L
s
.94594 .43277 m
.97506 .64585 L
s
.97506 .64585 m
.69286 .25814 L
s
.69286 .25814 m
.67932 .02494 L
s
.06024 .26735 m
.02494 .49015 L
s
.02494 .49015 m
.69286 .25814 L
s
.69286 .25814 m
.67932 .02494 L
s
.67932 .02494 m
.06024 .26735 L
s
.41001 .59401 m
.94594 .43277 L
s
.94594 .43277 m
.97506 .64585 L
s
.97506 .64585 m
.40296 .79562 L
s
.40296 .79562 m
.41001 .59401 L
s
P
p
.488 .116 .259 r
.0015 w
.38157 .75652 .40572 .71684 .44185 .70906 .41846 .7541 Metetra
.436 .078 .258 r
.41846 .7541 .44185 .70906 .47857 .69891 .45622 .74395 Metetra
.361 .005 .219 r
.45622 .74395 .47857 .69891 .51582 .68635 .4946 .72598 Metetra
.233 0 .119 r
.4946 .72598 .51582 .68635 .55355 .67155 .53336 .70081 Metetra
.53336 .70081 .55355 .67155 .59169 .65485 .57227 .66969 Metetra
.568 .887 .616 r
.57227 .66969 .59169 .65485 .6302 .63673 .61118 .63439 Metetra
.862 .958 .868 r
.61118 .63439 .6302 .63673 .66909 .61778 .65002 .59698 Metetra
.821 .786 .82 r
.65002 .59698 .66909 .61778 .70835 .59864 .68882 .5596 Metetra
.758 .67 .765 r
.68882 .5596 .70835 .59864 .74806 .57996 .7277 .52433 Metetra
.71 .599 .73 r
.7277 .52433 .74806 .57996 .78827 .56231 .7669 .49296 Metetra
.674 .553 .709 r
.7669 .49296 .78827 .56231 .8291 .54619 .8067 .46693 Metetra
.645 .522 .698 r
.8067 .46693 .8291 .54619 .87066 .53194 .84742 .44728 Metetra
.621 .501 .695 r
.84742 .44728 .87066 .53194 .91308 .51977 .88941 .43454 Metetra
.599 .487 .698 r
.88941 .43454 .91308 .51977 .95645 .50969 .933 .4288 Metetra
.535 .728 .963 r
.35883 .73801 .38157 .75652 .41846 .7541 .39601 .73538 Metetra
.646 .763 .934 r
.39601 .73538 .41846 .7541 .45622 .74395 .43412 .72496 Metetra
.72 .779 .899 r
.43412 .72496 .45622 .74395 .4946 .72598 .47292 .70663 Metetra
.767 .785 .868 r
.47292 .70663 .4946 .72598 .53336 .70081 .51216 .68101 Metetra
.796 .786 .844 r
.51216 .68101 .53336 .70081 .57227 .66969 .55159 .6494 Metetra
.813 .787 .828 r
.55159 .6494 .57227 .66969 .61118 .63439 .59104 .61356 Metetra
.821 .787 .82 r
.59104 .61356 .61118 .63439 .65002 .59698 .63043 .57559 Metetra
.821 .786 .82 r
.63043 .57559 .65002 .59698 .68882 .5596 .66977 .53767 Metetra
.812 .785 .827 r
.66977 .53767 .68882 .5596 .7277 .52433 .70919 .50186 Metetra
.795 .782 .841 r
.70919 .50186 .7277 .52433 .7669 .49296 .74891 .46998 Metetra
.765 .776 .862 r
.74891 .46998 .7669 .49296 .8067 .46693 .7892 .4435 Metetra
.722 .763 .885 r
.7892 .4435 .8067 .46693 .84742 .44728 .8304 .42344 Metetra
.663 .741 .908 r
.8304 .42344 .84742 .44728 .88941 .43454 .87286 .41034 Metetra
.591 .709 .925 r
.87286 .41034 .88941 .43454 .933 .4288 .9169 .40428 Metetra
.608 .498 .703 r
.33877 .65981 .35883 .73801 .39601 .73538 .37583 .65128 Metetra
.615 .495 .694 r
.37583 .65128 .39601 .73538 .43412 .72496 .41358 .64028 Metetra
.622 .5 .693 r
.41358 .64028 .43412 .72496 .47292 .70663 .45195 .62677 Metetra
.633 .513 .7 r
.45195 .62677 .47292 .70663 .51216 .68101 .49085 .61093 Metetra
.646 .537 .716 r
.49085 .61093 .51216 .68101 .55159 .6494 .53024 .5931 Metetra
.666 .576 .745 r
.53024 .5931 .55159 .6494 .59104 .61356 .57006 .57379 Metetra
.693 .645 .797 r
.57006 .57379 .59104 .61356 .63043 .57559 .61028 .55361 Metetra
.726 .772 .889 r
.61028 .55361 .63043 .57559 .66977 .53767 .65091 .53322 Metetra
.67 .945 .96 r
.65091 .53322 .66977 .53767 .70919 .50186 .692 .51328 Metetra
.692 .51328 .70919 .50186 .74891 .46998 .73361 .49441 Metetra
.197 0 0 r
.73361 .49441 .74891 .46998 .7892 .4435 .77585 .47709 Metetra
.328 0 .12 r
.77585 .47709 .7892 .4435 .8304 .42344 .81884 .4617 Metetra
.382 0 .182 r
.81884 .4617 .8304 .42344 .87286 .41034 .8627 .44844 Metetra
.402 .016 .188 r
.8627 .44844 .87286 .41034 .9169 .40428 .90756 .43732 Metetra
.622 .487 .676 r
.31997 .56475 .33877 .65981 .37583 .65128 .35687 .54905 Metetra
.607 .47 .67 r
.35687 .54905 .37583 .65128 .41358 .64028 .39405 .5374 Metetra
.594 .461 .67 r
.39405 .5374 .41358 .64028 .45195 .62677 .43165 .52987 Metetra
.582 .46 .679 r
.43165 .52987 .45195 .62677 .49085 .61093 .46985 .52617 Metetra
.571 .467 .698 r
.46985 .52617 .49085 .61093 .53024 .5931 .50881 .52573 Metetra
.558 .49 .735 r
.50881 .52573 .53024 .5931 .57006 .57379 .54868 .5277 Metetra
.535 .543 .81 r
.54868 .5277 .57006 .57379 .61028 .55361 .58955 .53099 Metetra
.439 .66 .955 r
.58955 .53099 .61028 .55361 .65091 .53322 .63148 .53437 Metetra
.102 0 0 r
.63148 .53437 .65091 .53322 .692 .51328 .67443 .53652 Metetra
.41 0 .02 r
.67443 .53652 .692 .51328 .73361 .49441 .71834 .53618 Metetra
.457 .068 .213 r
.71834 .53618 .73361 .49441 .77585 .47709 .76303 .53222 Metetra
.46 .109 .283 r
.76303 .53222 .77585 .47709 .81884 .4617 .80832 .52381 Metetra
.448 .114 .306 r
.80832 .52381 .81884 .4617 .8627 .44844 .85401 .51047 Metetra
.423 .093 .3 r
.85401 .51047 .8627 .44844 .90756 .43732 .89989 .49212 Metetra
.661 .557 .727 r
.29923 .50368 .31997 .56475 .35687 .54905 .33625 .48412 Metetra
.626 .53 .726 r
.33625 .48412 .35687 .54905 .39405 .5374 .37327 .472 Metetra
.589 .508 .732 r
.37327 .472 .39405 .5374 .43165 .52987 .41056 .46739 Metetra
.548 .492 .745 r
.41056 .46739 .43165 .52987 .46985 .52617 .44842 .46985 Metetra
.5 .483 .77 r
.44842 .46985 .46985 .52617 .50881 .52573 .48715 .47842 Metetra
.436 .482 .809 r
.48715 .47842 .50881 .52573 .54868 .5277 .52702 .49167 Metetra
.336 .492 .866 r
.52702 .49167 .54868 .5277 .58955 .53099 .56822 .50772 Metetra
.154 .497 .907 r
.56822 .50772 .58955 .53099 .63148 .53437 .61086 .52431 Metetra
0 .425 .768 r
.61086 .52431 .63148 .53437 .67443 .53652 .6549 .53903 Metetra
.329 0 0 r
.6549 .53903 .67443 .53652 .71834 .53618 .70018 .54943 Metetra
.364 0 0 r
.70018 .54943 .71834 .53618 .76303 .53222 .74644 .5533 Metetra
.325 0 0 r
.74644 .5533 .76303 .53222 .80832 .52381 .79328 .54895 Metetra
.245 0 0 r
.79328 .54895 .80832 .52381 .85401 .51047 .84028 .53538 Metetra
.117 0 0 r
.84028 .53538 .85401 .51047 .89989 .49212 .88706 .51249 Metetra
.663 .944 .96 r
.27372 .50225 .29923 .50368 .33625 .48412 .31124 .48413 Metetra
.479 .89 .928 r
.31124 .48413 .33625 .48412 .37327 .472 .34886 .47175 Metetra
.27 .774 .859 r
.34886 .47175 .37327 .472 .41056 .46739 .38677 .46518 Metetra
.116 .664 .815 r
.38677 .46518 .41056 .46739 .44842 .46985 .42524 .46406 Metetra
.053 .603 .832 r
.42524 .46406 .44842 .46985 .48715 .47842 .46451 .46761 Metetra
.087 .59 .894 r
.46451 .46761 .48715 .47842 .52702 .49167 .50479 .47467 Metetra
.191 .595 .944 r
.50479 .47467 .52702 .49167 .56822 .50772 .54626 .48375 Metetra
.314 .594 .944 r
.54626 .48375 .56822 .50772 .61086 .52431 .589 .49311 Metetra
.416 .586 .911 r
.589 .49311 .61086 .52431 .6549 .53903 .63301 .50088 Metetra
.493 .581 .871 r
.63301 .50088 .6549 .53903 .70018 .54943 .67818 .50522 Metetra
.552 .581 .837 r
.67818 .50522 .70018 .54943 .74644 .5533 .72431 .50449 Metetra
.598 .587 .812 r
.72431 .50449 .74644 .5533 .79328 .54895 .77111 .49743 Metetra
.636 .6 .796 r
.77111 .49743 .79328 .54895 .84028 .53538 .81828 .48332 Metetra
.671 .617 .787 r
.81828 .48332 .84028 .53538 .88706 .51249 .86552 .46208 Metetra
.355 0 .158 r
.24293 .54895 .27372 .50225 .31124 .48413 .28117 .5367 Metetra
.38 .009 .202 r
.28117 .5367 .31124 .48413 .34886 .47175 .32001 .52427 Metetra
.38 .009 .203 r
.32001 .52427 .34886 .47175 .38677 .46518 .35946 .51164 Metetra
.355 0 .158 r
.35946 .51164 .38677 .46518 .42524 .46406 .39953 .49881 Metetra
.283 0 .034 r
.39953 .49881 .42524 .46406 .46451 .46761 .44024 .48578 Metetra
.061 0 0 r
.44024 .48578 .46451 .46761 .50479 .47467 .48161 .47253 Metetra
.483 .889 .938 r
.48161 .47253 .50479 .47467 .54626 .48375 .52365 .45908 Metetra
.656 .751 .92 r
.52365 .45908 .54626 .48375 .589 .49311 .56637 .4454 Metetra
.64 .608 .801 r
.56637 .4454 .589 .49311 .63301 .50088 .6098 .43149 Metetra
.622 .536 .736 r
.6098 .43149 .63301 .50088 .67818 .50522 .65395 .41736 Metetra
.611 .498 .701 r
.65395 .41736 .67818 .50522 .72431 .50449 .69884 .40299 Metetra
.604 .479 .682 r
.69884 .40299 .72431 .50449 .77111 .49743 .74449 .38837 Metetra
.601 .47 .674 r
.74449 .38837 .77111 .49743 .81828 .48332 .79092 .37351 Metetra
.601 .47 .674 r
.79092 .37351 .81828 .48332 .86552 .46208 .83815 .35839 Metetra
.423 .037 .199 r
.20983 .59915 .24293 .54895 .28117 .5367 .2487 .59349 Metetra
.38 .009 .203 r
.2487 .59349 .28117 .5367 .32001 .52427 .28884 .58103 Metetra
.314 0 .167 r
.28884 .58103 .32001 .52427 .35946 .51164 .33004 .56168 Metetra
.203 0 .074 r
.33004 .56168 .35946 .51164 .39953 .49881 .37203 .53598 Metetra
.37203 .53598 .39953 .49881 .44024 .48578 .41456 .50501 Metetra
.487 .846 .592 r
.41456 .50501 .44024 .48578 .48161 .47253 .45738 .4703 Metetra
.83 .97 .89 r
.45738 .4703 .48161 .47253 .52365 .45908 .50034 .43365 Metetra
.803 .785 .836 r
.50034 .43365 .52365 .45908 .56637 .4454 .54338 .39693 Metetra
.737 .652 .765 r
.54338 .39693 .56637 .4454 .6098 .43149 .58654 .36195 Metetra
.688 .573 .721 r
.58654 .36195 .6098 .43149 .65395 .41736 .62994 .33029 Metetra
.651 .523 .694 r
.62994 .33029 .65395 .41736 .69884 .40299 .67382 .30323 Metetra
.624 .491 .68 r
.67382 .30323 .69884 .40299 .74449 .38837 .71845 .28166 Metetra
.601 .47 .674 r
.71845 .28166 .74449 .38837 .79092 .37351 .76415 .26607 Metetra
.582 .458 .676 r
.76415 .26607 .79092 .37351 .83815 .35839 .81126 .25651 Metetra
.129 .677 .779 r
.1798 .59971 .20983 .59915 .2487 .59349 .21903 .59603 Metetra
.393 .851 .871 r
.21903 .59603 .2487 .59349 .28884 .58103 .25987 .5833 Metetra
.626 .955 .922 r
.25987 .5833 .28884 .58103 .33004 .56168 .302 .56141 Metetra
.771 .973 .92 r
.302 .56141 .33004 .56168 .37203 .53598 .34506 .53109 Metetra
.838 .94 .892 r
.34506 .53109 .37203 .53598 .41456 .50501 .38867 .49387 Metetra
.856 .889 .86 r
.38867 .49387 .41456 .50501 .45738 .4703 .4325 .45184 Metetra
.85 .836 .833 r
.4325 .45184 .45738 .4703 .50034 .43365 .47632 .40743 Metetra
.829 .787 .812 r
.47632 .40743 .50034 .43365 .54338 .39693 .52001 .36315 Metetra
.8 .741 .798 r
.52001 .36315 .54338 .39693 .58654 .36195 .56361 .32135 Metetra
.766 .7 .789 r
.56361 .32135 .58654 .36195 .62994 .33029 .60729 .28405 Metetra
.727 .662 .786 r
.60729 .28405 .62994 .33029 .67382 .30323 .65131 .25287 Metetra
.686 .629 .786 r
.65131 .25287 .67382 .30323 .71845 .28166 .69604 .2289 Metetra
.64 .599 .791 r
.69604 .2289 .71845 .28166 .76415 .26607 .7419 .21276 Metetra
.592 .573 .801 r
.7419 .21276 .76415 .26607 .81126 .25651 .7893 .20448 Metetra
.59 .518 .742 r
.15636 .53119 .1798 .59971 .21903 .59603 .19579 .52278 Metetra
.612 .522 .728 r
.19579 .52278 .21903 .59603 .25987 .5833 .23642 .50944 Metetra
.633 .534 .725 r
.23642 .50944 .25987 .5833 .302 .56141 .27811 .4911 Metetra
.655 .555 .73 r
.27811 .4911 .302 .56141 .34506 .53109 .32065 .46813 Metetra
.68 .586 .743 r
.32065 .46813 .34506 .53109 .38867 .49387 .36385 .44127 Metetra
.708 .63 .768 r
.36385 .44127 .38867 .49387 .4325 .45184 .40753 .41161 Metetra
.74 .692 .806 r
.40753 .41161 .4325 .45184 .47632 .40743 .45154 .3804 Metetra
.773 .781 .86 r
.45154 .3804 .47632 .40743 .52001 .36315 .49583 .349 Metetra
.787 .894 .92 r
.49583 .349 .52001 .36315 .56361 .32135 .5404 .31872 Metetra
.711 .974 .924 r
.5404 .31872 .56361 .32135 .60729 .28405 .58533 .29073 Metetra
.471 .884 .763 r
.58533 .29073 .60729 .28405 .65131 .25287 .6308 .26598 Metetra
.6308 .26598 .65131 .25287 .69604 .2289 .677 .24515 Metetra
.677 .24515 .69604 .2289 .7419 .21276 .72418 .22861 Metetra
.074 0 0 r
.72418 .22861 .7419 .21276 .7893 .20448 .77259 .21639 Metetra
.601 .467 .67 r
.13735 .4268 .15636 .53119 .19579 .52278 .17696 .41056 Metetra
.588 .451 .662 r
.17696 .41056 .19579 .52278 .23642 .50944 .21692 .39646 Metetra
.58 .446 .662 r
.21692 .39646 .23642 .50944 .27811 .4911 .25733 .38451 Metetra
.575 .449 .671 r
.25733 .38451 .27811 .4911 .32065 .46813 .29832 .37455 Metetra
.573 .465 .693 r
.29832 .37455 .32065 .46813 .36385 .44127 .34 .36625 Metetra
.574 .499 .733 r
.34 .36625 .36385 .44127 .40753 .41161 .38252 .35911 Metetra
.577 .57 .809 r
.38252 .35911 .40753 .41161 .45154 .3804 .42598 .3525 Metetra
.558 .715 .945 r
.42598 .3525 .45154 .3804 .49583 .349 .47044 .34575 Metetra
.296 .786 .893 r
.47044 .34575 .49583 .349 .5404 .31872 .51596 .33811 Metetra
.144 0 0 r
.51596 .33811 .5404 .31872 .58533 .29073 .56252 .32892 Metetra
.29 0 .008 r
.56252 .32892 .58533 .29073 .6308 .26598 .61007 .31756 Metetra
.332 0 .119 r
.61007 .31756 .6308 .26598 .677 .24515 .65855 .3036 Metetra
.338 0 .157 r
.65855 .3036 .677 .24515 .72418 .22861 .70784 .28678 Metetra
.319 0 .151 r
.70784 .28678 .72418 .22861 .77259 .21639 .75784 .26707 Metetra
.634 .512 .697 r
.1163 .34239 .13735 .4268 .17696 .41056 .15622 .32051 Metetra
.6 .483 .691 r
.15622 .32051 .17696 .41056 .21692 .39646 .19576 .30575 Metetra
.567 .461 .693 r
.19576 .30575 .21692 .39646 .25733 .38451 .23524 .29817 Metetra
.533 .448 .705 r
.23524 .29817 .25733 .38451 .29832 .37455 .27498 .29732 Metetra
.494 .444 .73 r
.27498 .29732 .29832 .37455 .34 .36625 .31538 .3023 Metetra
.446 .454 .774 r
.31538 .3023 .34 .36625 .38252 .35911 .35681 .3117 Metetra
.369 .485 .846 r
.35681 .3117 .38252 .35911 .42598 .3525 .39958 .3237 Metetra
.211 .533 .923 r
.39958 .3237 .42598 .3525 .47044 .34575 .44394 .33615 Metetra
0 .482 .774 r
.44394 .33615 .47044 .34575 .51596 .33811 .49 .34668 Metetra
.285 0 0 r
.49 .34668 .51596 .33811 .56252 .32892 .53773 .35295 Metetra
.32 0 0 r
.53773 .35295 .56252 .32892 .61007 .31756 .58696 .35282 Metetra
.293 0 0 r
.58696 .35282 .61007 .31756 .65855 .3036 .63737 .34465 Metetra
.235 0 0 r
.63737 .34465 .65855 .3036 .70784 .28678 .68856 .32749 Metetra
.14 0 0 r
.68856 .32749 .70784 .28678 .75784 .26707 .74009 .30124 Metetra
.723 .756 .878 r
.08708 .3156 .1163 .34239 .15622 .32051 .12744 .29326 Metetra
.663 .743 .91 r
.12744 .29326 .15622 .32051 .19576 .30575 .16735 .27804 Metetra
.578 .717 .937 r
.16735 .27804 .19576 .30575 .23524 .29817 .20712 .27003 Metetra
.47 .673 .952 r
.20712 .27003 .23524 .29817 .27498 .29732 .24712 .26877 Metetra
.356 .617 .95 r
.24712 .26877 .27498 .29732 .31538 .3023 .28773 .27334 Metetra
.262 .564 .935 r
.28773 .27334 .31538 .3023 .35681 .3117 .32935 .28235 Metetra
.211 .534 .923 r
.32935 .28235 .35681 .3117 .39958 .3237 .37232 .29396 Metetra
.212 .534 .923 r
.37232 .29396 .39958 .3237 .44394 .33615 .41691 .306 Metetra
.261 .563 .935 r
.41691 .306 .44394 .33615 .49 .34668 .46326 .31611 Metetra
.348 .613 .949 r
.46326 .31611 .49 .34668 .53773 .35295 .51136 .32192 Metetra
.453 .67 .956 r
.51136 .32192 .53773 .35295 .58696 .35282 .56105 .32129 Metetra
.557 .721 .95 r
.56105 .32129 .58696 .35282 .63737 .34465 .61203 .31256 Metetra
.647 .76 .932 r
.61203 .31256 .63737 .34465 .68856 .32749 .66389 .29479 Metetra
.717 .787 .907 r
.66389 .29479 .68856 .32749 .74009 .30124 .7162 .26786 Metetra
.002 0 0 r
.04755 .34743 .08708 .3156 .12744 .29326 .08834 .32984 Metetra
.115 0 0 r
.08834 .32984 .12744 .29326 .16735 .27804 .12944 .31437 Metetra
.161 0 0 r
.12944 .31437 .16735 .27804 .20712 .27003 .17097 .30104 Metetra
.156 0 0 r
.17097 .30104 .20712 .27003 .24712 .26877 .21306 .28968 Metetra
.079 0 0 r
.21306 .28968 .24712 .26877 .28773 .27334 .25586 .27994 Metetra
.25586 .27994 .28773 .27334 .32935 .28235 .29951 .27132 Metetra
.434 .823 .986 r
.29951 .27132 .32935 .28235 .37232 .29396 .34415 .26321 Metetra
.56 .715 .945 r
.34415 .26321 .37232 .29396 .41691 .306 .38988 .25491 Metetra
.578 .6 .838 r
.38988 .25491 .41691 .306 .46326 .31611 .43676 .24567 Metetra
.579 .532 .767 r
.43676 .24567 .46326 .31611 .51136 .32192 .4848 .23482 Metetra
.58 .496 .724 r
.4848 .23482 .51136 .32192 .56105 .32129 .53398 .22175 Metetra
.583 .478 .7 r
.53398 .22175 .56105 .32129 .61203 .31256 .58423 .20601 Metetra
.589 .473 .688 r
.58423 .20601 .61203 .31256 .66389 .29479 .63544 .18735 Metetra
.599 .479 .687 r
.63544 .18735 .66389 .29479 .7162 .26786 .6875 .16572 Metetra
P
p
.002 w
.67932 .02494 m
.94594 .43277 L
s
.94594 .43277 m
.97506 .64585 L
s
.97506 .64585 m
.69286 .25814 L
s
.69286 .25814 m
.67932 .02494 L
s
.06024 .26735 m
.02494 .49015 L
s
.02494 .49015 m
.69286 .25814 L
s
.69286 .25814 m
.67932 .02494 L
s
.67932 .02494 m
.06024 .26735 L
s
P
p
p
.002 w
.06024 .26735 m
.67932 .02494 L
s
P
p
.002 w
.06024 .26735 m
.0648 .2716 L
s
P
[(-2)] .05113 .25884 1 .93395 Mshowa
p
.002 w
.20126 .21213 m
.2056 .21661 L
s
P
[(-1)] .1926 .20316 .96648 1 Mshowa
p
.002 w
.35089 .15354 m
.35496 .15826 L
s
P
[(0)] .34275 .1441 .86223 1 Mshowa
p
.002 w
.50994 .09126 m
.5137 .09623 L
s
P
[(1)] .50241 .08133 .75799 1 Mshowa
p
.002 w
.67932 .02494 m
.68274 .03015 L
s
P
[(2)] .6725 .0145 .65374 1 Mshowa
p
.001 w
.08779 .25656 m
.0905 .25914 L
s
P
p
.001 w
.11567 .24565 m
.11835 .24825 L
s
P
p
.001 w
.14386 .2346 m
.14652 .23724 L
s
P
p
.001 w
.17239 .22343 m
.17502 .22609 L
s
P
p
.001 w
.23048 .20069 m
.23305 .20341 L
s
P
p
.001 w
.26004 .18911 m
.26258 .19186 L
s
P
p
.001 w
.28996 .1774 m
.29247 .18017 L
s
P
p
.001 w
.32024 .16554 m
.32272 .16835 L
s
P
p
.001 w
.38192 .14139 m
.38433 .14425 L
s
P
p
.001 w
.41333 .12909 m
.4157 .13198 L
s
P
p
.001 w
.44513 .11664 m
.44747 .11956 L
s
P
p
.001 w
.47733 .10403 m
.47963 .10698 L
s
P
p
.001 w
.54296 .07833 m
.54518 .08134 L
s
P
p
.001 w
.5764 .06524 m
.57857 .06828 L
s
P
p
.001 w
.61027 .05198 m
.6124 .05505 L
s
P
p
.001 w
.64457 .03854 m
.64666 .04164 L
s
P
P
% End of Graphics
MathPictureEnd

:[font = output; output; inactive; preserveAspect; endGroup]
SurfaceGraphics["<<>>"]
;[o]
-SurfaceGraphics-
:[font = text; inactive; preserveAspect]
Here is a contour plot of the same function.
:[font = input; preserveAspect; startGroup]
Show[ ContourGraphics[ % ], ContourShading -> False,
                            ContourSmoothing -> True]
:[font = postscript; PostScript; formatAsPostScript; output; inactive; preserveAspect; pictureLeft = 100; pictureWidth = 300; pictureHeight = 300]
%!
%%Creator: Mathematica
%%AspectRatio: 1 
MathPictureStart
%% ContourGraphics
/Courier findfont 10  scalefont  setfont
% Scaling calculations
0.5 0.240385 0.5 0.240385 [
[(-2)] .01923 0 0 2 Msboxa
[(-1)] .25962 0 0 2 Msboxa
[(0)] .5 0 0 2 Msboxa
[(1)] .74038 0 0 2 Msboxa
[(2)] .98077 0 0 2 Msboxa
[(-2)] -0.0125 .01923 1 0 Msboxa
[(-1)] -0.0125 .25962 1 0 Msboxa
[(0)] -0.0125 .5 1 0 Msboxa
[(1)] -0.0125 .74038 1 0 Msboxa
[(2)] -0.0125 .98077 1 0 Msboxa
[ -0.001 -0.001 0 0 ]
[ 1.001 1.001 0 0 ]
] MathScale
% Start of Graphics
1 setlinecap
1 setlinejoin
newpath
[ ] 0 setdash
0 g
p
p
.002 w
.01923 0 m
.01923 .00625 L
s
P
[(-2)] .01923 0 0 2 Mshowa
p
.002 w
.25962 0 m
.25962 .00625 L
s
P
[(-1)] .25962 0 0 2 Mshowa
p
.002 w
.5 0 m
.5 .00625 L
s
P
[(0)] .5 0 0 2 Mshowa
p
.002 w
.74038 0 m
.74038 .00625 L
s
P
[(1)] .74038 0 0 2 Mshowa
p
.002 w
.98077 0 m
.98077 .00625 L
s
P
[(2)] .98077 0 0 2 Mshowa
p
.001 w
.06731 0 m
.06731 .00375 L
s
P
p
.001 w
.11538 0 m
.11538 .00375 L
s
P
p
.001 w
.16346 0 m
.16346 .00375 L
s
P
p
.001 w
.21154 0 m
.21154 .00375 L
s
P
p
.001 w
.30769 0 m
.30769 .00375 L
s
P
p
.001 w
.35577 0 m
.35577 .00375 L
s
P
p
.001 w
.40385 0 m
.40385 .00375 L
s
P
p
.001 w
.45192 0 m
.45192 .00375 L
s
P
p
.001 w
.54808 0 m
.54808 .00375 L
s
P
p
.001 w
.59615 0 m
.59615 .00375 L
s
P
p
.001 w
.64423 0 m
.64423 .00375 L
s
P
p
.001 w
.69231 0 m
.69231 .00375 L
s
P
p
.001 w
.78846 0 m
.78846 .00375 L
s
P
p
.001 w
.83654 0 m
.83654 .00375 L
s
P
p
.001 w
.88462 0 m
.88462 .00375 L
s
P
p
.001 w
.93269 0 m
.93269 .00375 L
s
P
p
.002 w
0 0 m
1 0 L
s
P
p
.002 w
0 .01923 m
.00625 .01923 L
s
P
[(-2)] -0.0125 .01923 1 0 Mshowa
p
.002 w
0 .25962 m
.00625 .25962 L
s
P
[(-1)] -0.0125 .25962 1 0 Mshowa
p
.002 w
0 .5 m
.00625 .5 L
s
P
[(0)] -0.0125 .5 1 0 Mshowa
p
.002 w
0 .74038 m
.00625 .74038 L
s
P
[(1)] -0.0125 .74038 1 0 Mshowa
p
.002 w
0 .98077 m
.00625 .98077 L
s
P
[(2)] -0.0125 .98077 1 0 Mshowa
p
.001 w
0 .06731 m
.00375 .06731 L
s
P
p
.001 w
0 .11538 m
.00375 .11538 L
s
P
p
.001 w
0 .16346 m
.00375 .16346 L
s
P
p
.001 w
0 .21154 m
.00375 .21154 L
s
P
p
.001 w
0 .30769 m
.00375 .30769 L
s
P
p
.001 w
0 .35577 m
.00375 .35577 L
s
P
p
.001 w
0 .40385 m
.00375 .40385 L
s
P
p
.001 w
0 .45192 m
.00375 .45192 L
s
P
p
.001 w
0 .54808 m
.00375 .54808 L
s
P
p
.001 w
0 .59615 m
.00375 .59615 L
s
P
p
.001 w
0 .64423 m
.00375 .64423 L
s
P
p
.001 w
0 .69231 m
.00375 .69231 L
s
P
p
.001 w
0 .78846 m
.00375 .78846 L
s
P
p
.001 w
0 .83654 m
.00375 .83654 L
s
P
p
.001 w
0 .88462 m
.00375 .88462 L
s
P
p
.001 w
0 .93269 m
.00375 .93269 L
s
P
p
.002 w
0 0 m
0 1 L
s
P
P
p
p
.002 w
.01923 .99375 m
.01923 1 L
s
P
p
.002 w
.25962 .99375 m
.25962 1 L
s
P
p
.002 w
.5 .99375 m
.5 1 L
s
P
p
.002 w
.74038 .99375 m
.74038 1 L
s
P
p
.002 w
.98077 .99375 m
.98077 1 L
s
P
p
.001 w
.06731 .99625 m
.06731 1 L
s
P
p
.001 w
.11538 .99625 m
.11538 1 L
s
P
p
.001 w
.16346 .99625 m
.16346 1 L
s
P
p
.001 w
.21154 .99625 m
.21154 1 L
s
P
p
.001 w
.30769 .99625 m
.30769 1 L
s
P
p
.001 w
.35577 .99625 m
.35577 1 L
s
P
p
.001 w
.40385 .99625 m
.40385 1 L
s
P
p
.001 w
.45192 .99625 m
.45192 1 L
s
P
p
.001 w
.54808 .99625 m
.54808 1 L
s
P
p
.001 w
.59615 .99625 m
.59615 1 L
s
P
p
.001 w
.64423 .99625 m
.64423 1 L
s
P
p
.001 w
.69231 .99625 m
.69231 1 L
s
P
p
.001 w
.78846 .99625 m
.78846 1 L
s
P
p
.001 w
.83654 .99625 m
.83654 1 L
s
P
p
.001 w
.88462 .99625 m
.88462 1 L
s
P
p
.001 w
.93269 .99625 m
.93269 1 L
s
P
p
.002 w
0 1 m
1 1 L
s
P
p
.002 w
.99375 .01923 m
1 .01923 L
s
P
p
.002 w
.99375 .25962 m
1 .25962 L
s
P
p
.002 w
.99375 .5 m
1 .5 L
s
P
p
.002 w
.99375 .74038 m
1 .74038 L
s
P
p
.002 w
.99375 .98077 m
1 .98077 L
s
P
p
.001 w
.99625 .06731 m
1 .06731 L
s
P
p
.001 w
.99625 .11538 m
1 .11538 L
s
P
p
.001 w
.99625 .16346 m
1 .16346 L
s
P
p
.001 w
.99625 .21154 m
1 .21154 L
s
P
p
.001 w
.99625 .30769 m
1 .30769 L
s
P
p
.001 w
.99625 .35577 m
1 .35577 L
s
P
p
.001 w
.99625 .40385 m
1 .40385 L
s
P
p
.001 w
.99625 .45192 m
1 .45192 L
s
P
p
.001 w
.99625 .54808 m
1 .54808 L
s
P
p
.001 w
.99625 .59615 m
1 .59615 L
s
P
p
.001 w
.99625 .64423 m
1 .64423 L
s
P
p
.001 w
.99625 .69231 m
1 .69231 L
s
P
p
.001 w
.99625 .78846 m
1 .78846 L
s
P
p
.001 w
.99625 .83654 m
1 .83654 L
s
P
p
.001 w
.99625 .88462 m
1 .88462 L
s
P
p
.001 w
.99625 .93269 m
1 .93269 L
s
P
p
.002 w
1 0 m
1 1 L
s
P
P
p
P
0 0 m
1 0 L
1 1 L
0 1 L
closepath
clip
newpath
p
p
.001 w
.08791 .83378 m
.15659 .83376 L
.21633 .84341 L
.21531 .91209 L
.15659 .92307 L
.08791 .92305 L
.02934 .91209 L
.02833 .84341 L
.08791 .83378 L
s
P
p
.001 w
.84341 .07693 m
.91209 .07695 L
.97066 .08791 L
.97167 .15659 L
.91209 .16622 L
.84341 .16624 L
.78367 .15659 L
.78469 .08791 L
.84341 .07693 L
s
P
p
.001 w
.01923 .40281 m
.08791 .41746 L
.15659 .41748 L
.22527 .40294 L
.2592 .36264 L
.22527 .3446 L
.15659 .33126 L
.08791 .33128 L
.01923 .34471 L
s
P
p
.001 w
.98077 .65529 m
.91209 .66872 L
.84341 .66874 L
.77473 .6554 L
.7408 .63736 L
.77473 .59706 L
.84341 .58252 L
.91209 .58254 L
.98077 .59719 L
s
P
p
.001 w
.01923 .94104 m
.08791 .94763 L
.15659 .94764 L
.22527 .94109 L
.29396 .9204 L
.30709 .91209 L
.30753 .84341 L
.29396 .83611 L
.22527 .81695 L
.15659 .81047 L
.08791 .81048 L
.01923 .817 L
s
P
p
.001 w
.01923 .43909 m
.08791 .45032 L
.15659 .45034 L
.22527 .43918 L
.25146 .43132 L
.29396 .41481 L
.32703 .36264 L
.29396 .33377 L
.22527 .31353 L
.15659 .30677 L
.08791 .30678 L
.01923 .31358 L
s
P
p
.001 w
.98077 .68642 m
.91209 .69322 L
.84341 .69323 L
.77473 .68647 L
.70604 .66623 L
.67297 .63736 L
.70604 .58519 L
.74854 .56868 L
.77473 .56082 L
.84341 .54966 L
.91209 .54968 L
.98077 .56091 L
s
P
p
.001 w
.98077 .183 m
.91209 .18952 L
.84341 .18953 L
.77473 .18305 L
.70604 .16389 L
.69247 .15659 L
.69291 .08791 L
.70604 .0796 L
.77473 .05891 L
.84341 .05236 L
.91209 .05237 L
.98077 .05896 L
s
P
p
.001 w
.01923 .96195 m
.08791 .96559 L
.15659 .9656 L
.22527 .96198 L
.29396 .95208 L
.36264 .92064 L
.37016 .91209 L
.37042 .84341 L
.36264 .8359 L
.29396 .80595 L
.22527 .79556 L
.15659 .79164 L
.08791 .79165 L
.01923 .79559 L
s
P
p
.001 w
.01923 .47183 m
.08791 .47641 L
.15659 .47642 L
.22527 .47187 L
.29396 .45753 L
.34037 .43132 L
.36264 .41505 L
.38189 .36264 L
.36264 .33354 L
.29396 .30209 L
.25128 .29396 L
.22527 .29105 L
.15659 .28656 L
.08791 .28657 L
.01923 .29109 L
s
P
p
.001 w
.98077 .70891 m
.91209 .71343 L
.84341 .71344 L
.77473 .70895 L
.74872 .70604 L
.70604 .69791 L
.63736 .66646 L
.61811 .63736 L
.63736 .58495 L
.65963 .56868 L
.70604 .54247 L
.77473 .52813 L
.84341 .52358 L
.91209 .52359 L
.98077 .52817 L
s
P
p
.001 w
.98077 .20441 m
.91209 .20835 L
.84341 .20836 L
.77473 .20444 L
.70604 .19405 L
.63736 .1641 L
.62958 .15659 L
.62984 .08791 L
.63736 .07936 L
.70604 .04792 L
.77473 .03802 L
.84341 .0344 L
.91209 .03441 L
.98077 .03805 L
s
P
p
.001 w
.01923 .97863 m
.08791 .98047 L
.15659 .98048 L
.22527 .97864 L
.29396 .97386 L
.36264 .96155 L
.42463 .91209 L
.42476 .84341 L
.36264 .79602 L
.29396 .78243 L
.22527 .77691 L
.15659 .77476 L
.08791 .77476 L
.01923 .77693 L
s
P
p
.001 w
.01923 .48923 m
.08791 .4906 L
.15659 .4906 L
.22527 .48924 L
.29396 .48519 L
.36264 .4713 L
.4088 .43132 L
.43091 .36264 L
.36868 .29396 L
.36264 .29159 L
.29396 .27648 L
.22527 .27075 L
.15659 .26857 L
.08791 .26857 L
.01923 .27077 L
s
P
p
.001 w
.98077 .72923 m
.91209 .73143 L
.84341 .73143 L
.77473 .72925 L
.70604 .72352 L
.63736 .70841 L
.63132 .70604 L
.56909 .63736 L
.5912 .56868 L
.63736 .5287 L
.70604 .51481 L
.77473 .51076 L
.84341 .5094 L
.91209 .5094 L
.98077 .51077 L
s
P
p
.001 w
.98077 .22307 m
.91209 .22524 L
.84341 .22524 L
.77473 .22309 L
.70604 .21757 L
.63736 .20398 L
.57524 .15659 L
.57537 .08791 L
.63736 .03845 L
.70604 .02614 L
.77473 .02136 L
.84341 .01952 L
.91209 .01953 L
.98077 .02137 L
s
P
p
.001 w
.01923 .49743 m
.08791 .49766 L
.15659 .49766 L
.22527 .49744 L
.29396 .49684 L
.36264 .49529 L
.43132 .48784 L
.47024 .43132 L
.4773 .36264 L
.45819 .29396 L
.43132 .27285 L
.36264 .25944 L
.29396 .25561 L
.22527 .25401 L
.15659 .25338 L
.08791 .25338 L
.01923 .25401 L
s
P
p
.001 w
.98077 .74599 m
.91209 .74662 L
.84341 .74662 L
.77473 .74599 L
.70604 .74439 L
.63736 .74056 L
.56868 .72715 L
.54181 .70604 L
.5227 .63736 L
.52976 .56868 L
.56868 .51216 L
.63736 .50471 L
.70604 .50316 L
.77473 .50256 L
.84341 .50234 L
.91209 .50234 L
.98077 .50257 L
s
P
p
.001 w
.01923 .76116 m
.08791 .76055 L
.15659 .76055 L
.22527 .76116 L
.29396 .76269 L
.36264 .76633 L
.41918 .77473 L
.43132 .77896 L
.47531 .84341 L
.47527 .91209 L
.43132 .97688 L
.41809 .98077 L
s
P
p
.001 w
.98077 .23884 m
.91209 .23945 L
.84341 .23945 L
.77473 .23884 L
.70604 .23731 L
.63736 .23367 L
.58082 .22527 L
.56868 .22104 L
.52469 .15659 L
.52473 .08791 L
.56868 .02312 L
.58191 .01923 L
s
P
p
.001 w
.01923 .74599 m
.08791 .74662 L
.15659 .74662 L
.22527 .74599 L
.29396 .74439 L
.36264 .74056 L
.43132 .72715 L
.45819 .70604 L
.4773 .63736 L
.47024 .56868 L
.43132 .51216 L
.36264 .50471 L
.29396 .50316 L
.22527 .50256 L
.15659 .50234 L
.08791 .50234 L
.01923 .50257 L
s
P
p
.001 w
.98077 .49743 m
.91209 .49766 L
.84341 .49766 L
.77473 .49744 L
.70604 .49684 L
.63736 .49529 L
.56868 .48784 L
.52976 .43132 L
.5227 .36264 L
.54181 .29396 L
.56868 .27285 L
.63736 .25944 L
.70604 .25561 L
.77473 .25401 L
.84341 .25338 L
.91209 .25338 L
.98077 .25401 L
s
P
p
.001 w
.98077 .76116 m
.91209 .76055 L
.84341 .76055 L
.77473 .76116 L
.70604 .76269 L
.63736 .76633 L
.58082 .77473 L
.56868 .77896 L
.52469 .84341 L
.52473 .91209 L
.56868 .97688 L
.58191 .98077 L
s
P
p
.001 w
.01923 .23884 m
.08791 .23945 L
.15659 .23945 L
.22527 .23884 L
.29396 .23731 L
.36264 .23367 L
.41918 .22527 L
.43132 .22104 L
.47531 .15659 L
.47527 .08791 L
.43132 .02312 L
.41809 .01923 L
s
P
p
.001 w
.01923 .72923 m
.08791 .73143 L
.15659 .73143 L
.22527 .72925 L
.29396 .72352 L
.36264 .70841 L
.36868 .70604 L
.43091 .63736 L
.4088 .56868 L
.36264 .5287 L
.29396 .51481 L
.22527 .51076 L
.15659 .5094 L
.08791 .5094 L
.01923 .51077 L
s
P
p
.001 w
.01923 .22307 m
.08791 .22524 L
.15659 .22524 L
.22527 .22309 L
.29396 .21757 L
.36264 .20398 L
.42476 .15659 L
.42463 .08791 L
.36264 .03845 L
.29396 .02614 L
.22527 .02136 L
.15659 .01952 L
.08791 .01953 L
.01923 .02137 L
s
P
p
.001 w
.98077 .97863 m
.91209 .98047 L
.84341 .98048 L
.77473 .97864 L
.70604 .97386 L
.63736 .96155 L
.57537 .91209 L
.57524 .84341 L
.63736 .79602 L
.70604 .78243 L
.77473 .77691 L
.84341 .77476 L
.91209 .77476 L
.98077 .77693 L
s
P
p
.001 w
.98077 .48923 m
.91209 .4906 L
.84341 .4906 L
.77473 .48924 L
.70604 .48519 L
.63736 .4713 L
.5912 .43132 L
.56909 .36264 L
.63132 .29396 L
.63736 .29159 L
.70604 .27648 L
.77473 .27075 L
.84341 .26857 L
.91209 .26857 L
.98077 .27077 L
s
P
p
.001 w
.01923 .70891 m
.08791 .71343 L
.15659 .71344 L
.22527 .70895 L
.25128 .70604 L
.29396 .69791 L
.36264 .66646 L
.38189 .63736 L
.36264 .58495 L
.34037 .56868 L
.29396 .54247 L
.22527 .52813 L
.15659 .52358 L
.08791 .52359 L
.01923 .52817 L
s
P
p
.001 w
.01923 .20441 m
.08791 .20835 L
.15659 .20836 L
.22527 .20444 L
.29396 .19405 L
.36264 .1641 L
.37042 .15659 L
.37016 .08791 L
.36264 .07936 L
.29396 .04792 L
.22527 .03802 L
.15659 .0344 L
.08791 .03441 L
.01923 .03805 L
s
P
p
.001 w
.98077 .96195 m
.91209 .96559 L
.84341 .9656 L
.77473 .96198 L
.70604 .95208 L
.63736 .92064 L
.62984 .91209 L
.62958 .84341 L
.63736 .8359 L
.70604 .80595 L
.77473 .79556 L
.84341 .79164 L
.91209 .79165 L
.98077 .79559 L
s
P
p
.001 w
.98077 .47183 m
.91209 .47641 L
.84341 .47642 L
.77473 .47187 L
.70604 .45753 L
.65963 .43132 L
.63736 .41505 L
.61811 .36264 L
.63736 .33354 L
.70604 .30209 L
.74872 .29396 L
.77473 .29105 L
.84341 .28656 L
.91209 .28657 L
.98077 .29109 L
s
P
p
.001 w
.01923 .68642 m
.08791 .69322 L
.15659 .69323 L
.22527 .68647 L
.29396 .66623 L
.32703 .63736 L
.29396 .58519 L
.25146 .56868 L
.22527 .56082 L
.15659 .54966 L
.08791 .54968 L
.01923 .56091 L
s
P
p
.001 w
.01923 .183 m
.08791 .18952 L
.15659 .18953 L
.22527 .18305 L
.29396 .16389 L
.30753 .15659 L
.30709 .08791 L
.29396 .0796 L
.22527 .05891 L
.15659 .05236 L
.08791 .05237 L
.01923 .05896 L
s
P
p
.001 w
.98077 .94104 m
.91209 .94763 L
.84341 .94764 L
.77473 .94109 L
.70604 .9204 L
.69291 .91209 L
.69247 .84341 L
.70604 .83611 L
.77473 .81695 L
.84341 .81047 L
.91209 .81048 L
.98077 .817 L
s
P
p
.001 w
.98077 .43909 m
.91209 .45032 L
.84341 .45034 L
.77473 .43918 L
.74854 .43132 L
.70604 .41481 L
.67297 .36264 L
.70604 .33377 L
.77473 .31353 L
.84341 .30677 L
.91209 .30678 L
.98077 .31358 L
s
P
p
.001 w
.84341 .83376 m
.91209 .83378 L
.97167 .84341 L
.97066 .91209 L
.91209 .92305 L
.84341 .92307 L
.78469 .91209 L
.78367 .84341 L
.84341 .83376 L
s
P
p
.001 w
.08791 .07695 m
.15659 .07693 L
.21531 .08791 L
.21633 .15659 L
.15659 .16624 L
.08791 .16622 L
.02833 .15659 L
.02934 .08791 L
.08791 .07695 L
s
P
p
.001 w
.01923 .65529 m
.08791 .66872 L
.15659 .66874 L
.22527 .6554 L
.2592 .63736 L
.22527 .59706 L
.15659 .58252 L
.08791 .58254 L
.01923 .59719 L
s
P
p
.001 w
.98077 .40281 m
.91209 .41746 L
.84341 .41748 L
.77473 .40294 L
.7408 .36264 L
.77473 .3446 L
.84341 .33126 L
.91209 .33128 L
.98077 .34471 L
s
P
P
% End of Graphics
MathPictureEnd

:[font = output; output; inactive; preserveAspect; endGroup]
ContourGraphics["<<>>"]
;[o]
-ContourGraphics-
:[font = text; inactive; preserveAspect]
This generates a three-dimensional parametric surface.
:[font = input; preserveAspect; startGroup]
ParametricPlot3D[ {u Sin[t], u Cos[t], t/3},
              {t, 0, 12}, {u, -1, 1}, Ticks -> None]
:[font = postscript; PostScript; formatAsPostScript; output; inactive; preserveAspect; pictureLeft = 100; pictureWidth = 187; pictureHeight = 299.812]
%!
%%Creator: Mathematica
%%AspectRatio: 1.60358 
MathPictureStart
%% Graphics3D
/Courier findfont 10  scalefont  setfont
% Scaling calculations
-0.256436 1.53812 0.0270106 1.53812 [
[ 0 0 0 0 ]
[ 1 1.60358 0 0 ]
] MathScale
% Start of Graphics
1 setlinecap
1 setlinejoin
newpath
[ ] 0 setdash
0 g
p
p
.002 w
.39312 1.56513 m
.96155 1.42592 L
s
P
P
p
p
.002 w
.03845 1.28432 m
.39312 1.56513 L
s
P
P
p
p
.002 w
.11641 .24018 m
.03845 1.28432 L
s
P
P
0 0 m
1 0 L
1 1.60358 L
0 1.60358 L
closepath
clip
newpath
p
.002 w
.11641 .24018 m
.03845 1.28432 L
s
.03845 1.28432 m
.39312 1.56513 L
s
.39312 1.56513 m
.411 .57272 L
s
.411 .57272 m
.11641 .24018 L
s
.63035 .03845 m
.89105 .40641 L
s
.89105 .40641 m
.96155 1.42592 L
s
.96155 1.42592 m
.65505 1.10909 L
s
.65505 1.10909 m
.63035 .03845 L
s
.11641 .24018 m
.03845 1.28432 L
s
.03845 1.28432 m
.65505 1.10909 L
s
.65505 1.10909 m
.63035 .03845 L
s
.63035 .03845 m
.11641 .24018 L
s
.411 .57272 m
.89105 .40641 L
s
.89105 .40641 m
.96155 1.42592 L
s
.96155 1.42592 m
.39312 1.56513 L
s
.39312 1.56513 m
.411 .57272 L
s
P
p
.002 w
.76118 .46866 m .63967 .50436 L .62683 .48834 L p
.507 .745 .981 r
F P
s
P
p
.002 w
.62683 .48834 m .73554 .46054 L .76118 .46866 L p
.507 .745 .981 r
F P
s
P
p
.002 w
.73554 .46054 m .62683 .48834 L .61386 .47217 L p
.468 .74 .987 r
F P
s
P
p
.002 w
.61386 .47217 m .70977 .45239 L .73554 .46054 L p
.468 .74 .987 r
F P
s
P
p
.002 w
.70977 .45239 m .61386 .47217 L .60078 .45585 L p
.408 .727 .99 r
F P
s
P
p
.002 w
.60078 .45585 m .68389 .4442 L .70977 .45239 L p
.408 .727 .99 r
F P
s
P
p
.002 w
.68389 .4442 m .60078 .45585 L .58757 .43938 L p
.308 .695 .978 r
F P
s
P
p
.002 w
.58757 .43938 m .65788 .43597 L .68389 .4442 L p
.308 .695 .978 r
F P
s
P
p
.002 w
.65788 .43597 m .58757 .43938 L .57423 .42274 L p
.127 .611 .915 r
F P
s
P
p
.002 w
.57423 .42274 m .63175 .4277 L .65788 .43597 L p
.127 .611 .915 r
F P
s
P
p
.002 w
.63175 .4277 m .57423 .42274 L .56077 .40595 L p
0 .394 .694 r
F P
s
P
p
.002 w
.63505 .71146 m .48343 .68357 L .48018 .70108 L p
.509 .679 .942 r
F P
s
P
p
.002 w
.48018 .70108 m .64879 .72692 L .63505 .71146 L p
.509 .679 .942 r
F P
s
P
p
.002 w
.32497 .61129 m .48018 .70108 L .48343 .68357 L p
.567 .641 .883 r
F P
s
P
p
.002 w
.56077 .40595 m .6055 .41939 L .63175 .4277 L p
0 .394 .694 r
F P
s
P
p
.002 w
.6055 .41939 m .56077 .40595 L .54718 .389 L p
.564 0 0 r
F P
s
P
p
.002 w
.79402 .39941 m .76118 .46866 L .73554 .46054 L p
.585 .836 .992 r
F P
s
P
p
.002 w
.62116 .69586 m .48673 .66587 L .48343 .68357 L p
.486 .668 .944 r
F P
s
P
p
.002 w
.48343 .68357 m .63505 .71146 L .62116 .69586 L p
.486 .668 .944 r
F P
s
P
p
.002 w
.34405 .59798 m .48343 .68357 L .48673 .66587 L p
.555 .629 .88 r
F P
s
P
p
.002 w
.48343 .68357 m .34405 .59798 L .32497 .61129 L p
.567 .641 .883 r
F P
s
P
p
.002 w
.23679 .46956 m .32497 .61129 L .34405 .59798 L p
.628 .637 .84 r
F P
s
P
p
.002 w
.54718 .389 m .57912 .41105 L .6055 .41939 L p
.564 0 0 r
F P
s
P
p
.002 w
.57912 .41105 m .54718 .389 L .53346 .37189 L p
.754 .314 .207 r
F P
s
P
p
.002 w
.73554 .46054 m .76414 .40317 L .79402 .39941 L p
.585 .836 .992 r
F P
s
P
p
.002 w
.76414 .40317 m .73554 .46054 L .70977 .45239 L p
.558 .845 .998 r
F P
s
P
p
.002 w
.48673 .66587 m .36329 .58456 L .34405 .59798 L p
.555 .629 .88 r
F P
s
P
p
.002 w
.26557 .46594 m .34405 .59798 L .36329 .58456 L p
.623 .628 .834 r
F P
s
P
p
.002 w
.34405 .59798 m .26557 .46594 L .23679 .46956 L p
.628 .637 .84 r
F P
s
P
p
.002 w
.70977 .45239 m .73431 .40693 L .76414 .40317 L p
.558 .845 .998 r
F P
s
P
p
.002 w
.73431 .40693 m .70977 .45239 L .68389 .4442 L p
.513 .851 .996 r
F P
s
P
p
.002 w
.60715 .6801 m .49006 .64798 L .48673 .66587 L p
.454 .653 .945 r
F P
s
P
p
.002 w
.48673 .66587 m .62116 .69586 L .60715 .6801 L p
.454 .653 .945 r
F P
s
P
p
.002 w
.36329 .58456 m .48673 .66587 L .49006 .64798 L p
.54 .615 .875 r
F P
s
P
p
.002 w
.68389 .4442 m .70455 .41067 L .73431 .40693 L p
.513 .851 .996 r
F P
s
P
p
.002 w
.70455 .41067 m .68389 .4442 L .65788 .43597 L p
.432 .841 .971 r
F P
s
P
p
.002 w
.53346 .37189 m .55262 .40266 L .57912 .41105 L p
.754 .314 .207 r
F P
s
P
p
.002 w
.55262 .40266 m .53346 .37189 L .5196 .35461 L p
.798 .484 .458 r
F P
s
P
p
.002 w
.49006 .64798 m .38268 .57104 L .36329 .58456 L p
.54 .615 .875 r
F P
s
P
p
.002 w
.29441 .46231 m .36329 .58456 L .38268 .57104 L p
.618 .616 .826 r
F P
s
P
p
.002 w
.36329 .58456 m .29441 .46231 L .26557 .46594 L p
.623 .628 .834 r
F P
s
P
p
.002 w
.74674 .68812 m .63505 .71146 L .64879 .72692 L p
.493 .755 .988 r
F P
s
P
p
.002 w
.64879 .72692 m .77367 .69576 L .74674 .68812 L p
.493 .755 .988 r
F P
s
P
p
.002 w
.65788 .43597 m .67485 .41441 L .70455 .41067 L p
.432 .841 .971 r
F P
s
P
p
.002 w
.67485 .41441 m .65788 .43597 L .63175 .4277 L p
.271 .773 .87 r
F P
s
P
p
.002 w
.593 .6642 m .49343 .62989 L .49006 .64798 L p
.408 .631 .945 r
F P
s
P
p
.002 w
.49006 .64798 m .60715 .6801 L .593 .6642 L p
.408 .631 .945 r
F P
s
P
p
.002 w
.38268 .57104 m .49006 .64798 L .49343 .62989 L p
.521 .596 .869 r
F P
s
P
p
.002 w
.63175 .4277 m .64521 .41814 L .67485 .41441 L p
.271 .773 .87 r
F P
s
P
p
.002 w
.64521 .41814 m .63175 .4277 L .6055 .41939 L p
.019 0 0 r
F P
s
P
p
.002 w
.5196 .35461 m .52599 .39423 L .55262 .40266 L p
.798 .484 .458 r
F P
s
P
p
.002 w
.52599 .39423 m .5196 .35461 L .50561 .33716 L p
.797 .568 .59 r
F P
s
P
p
.002 w
.49343 .62989 m .40223 .55741 L .38268 .57104 L p
.521 .596 .869 r
F P
s
P
p
.002 w
.32331 .45867 m .38268 .57104 L .40223 .55741 L p
.611 .601 .816 r
F P
s
P
p
.002 w
.38268 .57104 m .32331 .45867 L .29441 .46231 L p
.618 .616 .826 r
F P
s
P
p
.002 w
.71968 .68045 m .62116 .69586 L .63505 .71146 L p
.459 .749 .992 r
F P
s
P
p
.002 w
.63505 .71146 m .74674 .68812 L .71968 .68045 L p
.459 .749 .992 r
F P
s
P
p
.002 w
.6055 .41939 m .61564 .42187 L .64521 .41814 L p
.019 0 0 r
F P
s
P
p
.002 w
.61564 .42187 m .6055 .41939 L .57912 .41105 L p
.347 0 0 r
F P
s
P
p
.002 w
.49683 .6116 m .42193 .54366 L .40223 .55741 L p
.495 .57 .86 r
F P
s
P
p
.002 w
.40223 .55741 m .49343 .62989 L .49683 .6116 L p
.495 .57 .86 r
F P
s
P
p
.002 w
.35226 .45502 m .40223 .55741 L .42193 .54366 L p
.603 .583 .804 r
F P
s
P
p
.002 w
.40223 .55741 m .35226 .45502 L .32331 .45867 L p
.611 .601 .816 r
F P
s
P
p
.002 w
.57912 .41105 m .58613 .42558 L .61564 .42187 L p
.347 0 0 r
F P
s
P
p
.002 w
.58613 .42558 m .57912 .41105 L .55262 .40266 L p
.54 .154 .259 r
F P
s
P
p
.002 w
.57871 .64813 m .49683 .6116 L .49343 .62989 L p
.34 .595 .94 r
F P
s
P
p
.002 w
.49343 .62989 m .593 .6642 L .57871 .64813 L p
.34 .595 .94 r
F P
s
P
p
.002 w
.50561 .33716 m .49923 .38577 L .52599 .39423 L p
.797 .568 .59 r
F P
s
P
p
.002 w
.49923 .38577 m .50561 .33716 L .49149 .31954 L p
.786 .613 .666 r
F P
s
P
p
.002 w
.6925 .67275 m .60715 .6801 L .62116 .69586 L p
.409 .738 .992 r
F P
s
P
p
.002 w
.62116 .69586 m .71968 .68045 L .6925 .67275 L p
.409 .738 .992 r
F P
s
P
p
.002 w
.55262 .40266 m .55668 .42929 L .58613 .42558 L p
.54 .154 .259 r
F P
s
P
p
.002 w
.55668 .42929 m .55262 .40266 L .52599 .39423 L p
.621 .341 .473 r
F P
s
P
p
.002 w
.50027 .59311 m .4418 .52981 L .42193 .54366 L p
.458 .533 .847 r
F P
s
P
p
.002 w
.42193 .54366 m .49683 .6116 L .50027 .59311 L p
.458 .533 .847 r
F P
s
P
p
.002 w
.38128 .45137 m .42193 .54366 L .4418 .52981 L p
.591 .559 .787 r
F P
s
P
p
.002 w
.42193 .54366 m .38128 .45137 L .35226 .45502 L p
.603 .583 .804 r
F P
s
P
p
.002 w
.25255 .30771 m .23679 .46956 L .26557 .46594 L p
.682 .659 .821 r
F P
s
P
p
.002 w
.52599 .39423 m .52729 .43299 L .55668 .42929 L p
.621 .341 .473 r
F P
s
P
p
.002 w
.52729 .43299 m .52599 .39423 L .49923 .38577 L p
.655 .448 .592 r
F P
s
P
p
.002 w
.49149 .31954 m .47235 .37726 L .49923 .38577 L p
.786 .613 .666 r
F P
s
P
p
.002 w
.47235 .37726 m .49149 .31954 L .47722 .30175 L p
.774 .64 .714 r
F P
s
P
p
.002 w
.28049 .31655 m .26557 .46594 L .29441 .46231 L p
.682 .652 .814 r
F P
s
P
p
.002 w
.26557 .46594 m .28049 .31655 L .25255 .30771 L p
.682 .659 .821 r
F P
s
P
p
.002 w
.56427 .63191 m .50027 .59311 L .49683 .6116 L p
.231 .532 .921 r
F P
s
P
p
.002 w
.49683 .6116 m .57871 .64813 L .56427 .63191 L p
.231 .532 .921 r
F P
s
P
p
.002 w
.3083 .32535 m .29441 .46231 L .32331 .45867 L p
.682 .644 .805 r
F P
s
P
p
.002 w
.29441 .46231 m .3083 .32535 L .28049 .31655 L p
.682 .652 .814 r
F P
s
P
p
.002 w
.66519 .66501 m .593 .6642 L .60715 .6801 L p
.332 .715 .983 r
F P
s
P
p
.002 w
.60715 .6801 m .6925 .67275 L .66519 .66501 L p
.332 .715 .983 r
F P
s
P
p
.002 w
.49923 .38577 m .49797 .43668 L .52729 .43299 L p
.655 .448 .592 r
F P
s
P
p
.002 w
.49797 .43668 m .49923 .38577 L .47235 .37726 L p
.67 .512 .663 r
F P
s
P
p
.002 w
.33597 .33411 m .32331 .45867 L .35226 .45502 L p
.682 .634 .794 r
F P
s
P
p
.002 w
.32331 .45867 m .33597 .33411 L .3083 .32535 L p
.682 .644 .805 r
F P
s
P
p
.002 w
.50375 .57441 m .46183 .51584 L .4418 .52981 L p
.403 .478 .823 r
F P
s
P
p
.002 w
.4418 .52981 m .50027 .59311 L .50375 .57441 L p
.403 .478 .823 r
F P
s
P
p
.002 w
.41036 .44771 m .4418 .52981 L .46183 .51584 L p
.575 .526 .763 r
F P
s
P
p
.002 w
.4418 .52981 m .41036 .44771 L .38128 .45137 L p
.591 .559 .787 r
F P
s
P
p
.002 w
.36351 .34282 m .35226 .45502 L .38128 .45137 L p
.682 .621 .781 r
F P
s
P
p
.002 w
.35226 .45502 m .36351 .34282 L .33597 .33411 L p
.682 .634 .794 r
F P
s
P
p
.002 w
.39092 .35149 m .38128 .45137 L .41036 .44771 L p
.682 .605 .763 r
F P
s
P
p
.002 w
.38128 .45137 m .39092 .35149 L .36351 .34282 L p
.682 .621 .781 r
F P
s
P
p
.002 w
.47235 .37726 m .46871 .44036 L .49797 .43668 L p
.67 .512 .663 r
F P
s
P
p
.002 w
.46871 .44036 m .47235 .37726 L .44533 .36871 L p
.677 .554 .709 r
F P
s
P
p
.002 w
.47722 .30175 m .44533 .36871 L .47235 .37726 L p
.774 .64 .714 r
F P
s
P
p
.002 w
.44533 .36871 m .47722 .30175 L .46281 .28378 L p
.764 .658 .746 r
F P
s
P
p
.002 w
.46183 .51584 m .4395 .44404 L .41036 .44771 L p
.575 .526 .763 r
F P
s
P
p
.002 w
.41819 .36012 m .41036 .44771 L .4395 .44404 L p
.68 .584 .741 r
F P
s
P
p
.002 w
.41036 .44771 m .41819 .36012 L .39092 .35149 L p
.682 .605 .763 r
F P
s
P
p
.002 w
.5497 .61553 m .50375 .57441 L .50027 .59311 L p
.037 .408 .859 r
F P
s
P
p
.002 w
.50027 .59311 m .56427 .63191 L .5497 .61553 L p
.037 .408 .859 r
F P
s
P
p
.002 w
.48202 .50175 m .46871 .44036 L .4395 .44404 L p
.55 .48 .729 r
F P
s
P
p
.002 w
.4395 .44404 m .46183 .51584 L .48202 .50175 L p
.55 .48 .729 r
F P
s
P
p
.002 w
.44533 .36871 m .4395 .44404 L .46871 .44036 L p
.677 .554 .709 r
F P
s
P
p
.002 w
.4395 .44404 m .44533 .36871 L .41819 .36012 L p
.68 .584 .741 r
F P
s
P
p
.002 w
.63774 .65723 m .57871 .64813 L .593 .6642 L p
.207 .662 .946 r
F P
s
P
p
.002 w
.593 .6642 m .66519 .66501 L .63774 .65723 L p
.207 .662 .946 r
F P
s
P
p
.002 w
.46281 .28378 m .41819 .36012 L .44533 .36871 L p
.764 .658 .746 r
F P
s
P
p
.002 w
.50727 .5555 m .48202 .50175 L .46183 .51584 L p
.311 .386 .778 r
F P
s
P
p
.002 w
.46183 .51584 m .50375 .57441 L .50727 .5555 L p
.311 .386 .778 r
F P
s
P
p
.002 w
.50238 .48755 m .49797 .43668 L .46871 .44036 L p
.512 .409 .675 r
F P
s
P
p
.002 w
.46871 .44036 m .48202 .50175 L .50238 .48755 L p
.512 .409 .675 r
F P
s
P
p
.002 w
.52291 .47323 m .52729 .43299 L .49797 .43668 L p
.445 .293 .58 r
F P
s
P
p
.002 w
.49797 .43668 m .50238 .48755 L .52291 .47323 L p
.445 .293 .58 r
F P
s
P
p
.002 w
.41819 .36012 m .46281 .28378 L .44827 .26563 L p
.755 .671 .768 r
F P
s
P
p
.002 w
.44827 .26563 m .39092 .35149 L .41819 .36012 L p
.755 .671 .768 r
F P
s
P
p
.002 w
.54361 .45879 m .55668 .42929 L .52729 .43299 L p
.315 .087 .399 r
F P
s
P
p
.002 w
.52729 .43299 m .52291 .47323 L .54361 .45879 L p
.315 .087 .399 r
F P
s
P
p
.002 w
.56448 .44424 m .58613 .42558 L .55668 .42929 L p
.053 0 .037 r
F P
s
P
p
.002 w
.55668 .42929 m .54361 .45879 L .56448 .44424 L p
.053 0 .037 r
F P
s
P
p
.002 w
.51083 .53638 m .50238 .48755 L .48202 .50175 L p
.142 .214 .679 r
F P
s
P
p
.002 w
.48202 .50175 m .50727 .5555 L .51083 .53638 L p
.142 .214 .679 r
F P
s
P
p
.002 w
.53498 .59898 m .50727 .5555 L .50375 .57441 L p
0 .143 .657 r
F P
s
P
p
.002 w
.50375 .57441 m .5497 .61553 L .53498 .59898 L p
0 .143 .657 r
F P
s
P
p
.002 w
.39092 .35149 m .44827 .26563 L .43357 .24731 L p
.747 .679 .785 r
F P
s
P
p
.002 w
.43357 .24731 m .36351 .34282 L .39092 .35149 L p
.747 .679 .785 r
F P
s
P
p
.002 w
.58553 .42956 m .61564 .42187 L .58613 .42558 L p
0 0 0 r
F P
s
P
p
.002 w
.58613 .42558 m .56448 .44424 L .58553 .42956 L p
0 0 0 r
F P
s
P
p
.002 w
.61017 .64941 m .56427 .63191 L .57871 .64813 L p
0 .54 .833 r
F P
s
P
p
.002 w
.57871 .64813 m .63774 .65723 L .61017 .64941 L p
0 .54 .833 r
F P
s
P
p
.002 w
.60675 .41475 m .64521 .41814 L .61564 .42187 L p
.586 .944 .819 r
F P
s
P
p
.002 w
.61564 .42187 m .58553 .42956 L .60675 .41475 L p
.586 .944 .819 r
F P
s
P
p
.002 w
.65865 .96828 m .48346 .9394 L .48638 .92247 L p
.441 .668 .959 r
F P
s
P
p
.002 w
.48346 .9394 m .32074 .84879 L .34021 .83573 L p
.533 .631 .894 r
F P
s
P
p
.002 w
.34021 .83573 m .48638 .92247 L .48346 .9394 L p
.533 .631 .894 r
F P
s
P
p
.002 w
.62816 .39982 m .67485 .41441 L .64521 .41814 L p
.675 .965 .931 r
F P
s
P
p
.002 w
.64521 .41814 m .60675 .41475 L .62816 .39982 L p
.675 .965 .931 r
F P
s
P
p
.002 w
.36351 .34282 m .43357 .24731 L .41873 .2288 L p
.741 .686 .798 r
F P
s
P
p
.002 w
.41873 .2288 m .33597 .33411 L .36351 .34282 L p
.741 .686 .798 r
F P
s
P
p
.002 w
.64975 .38477 m .70455 .41067 L .67485 .41441 L p
.701 .942 .96 r
F P
s
P
p
.002 w
.67485 .41441 m .62816 .39982 L .64975 .38477 L p
.701 .942 .96 r
F P
s
P
p
.002 w
.51443 .51704 m .52291 .47323 L .50238 .48755 L p
0 0 .42 r
F P
s
P
p
.002 w
.50238 .48755 m .51083 .53638 L .51443 .51704 L p
0 0 .42 r
F P
s
P
p
.002 w
.67152 .36958 m .73431 .40693 L .70455 .41067 L p
.707 .915 .966 r
F P
s
P
p
.002 w
.70455 .41067 m .64975 .38477 L .67152 .36958 L p
.707 .915 .966 r
F P
s
P
p
.002 w
.33597 .33411 m .41873 .2288 L .40374 .2101 L p
.735 .691 .809 r
F P
s
P
p
.002 w
.40374 .2101 m .3083 .32535 L .33597 .33411 L p
.735 .691 .809 r
F P
s
P
p
.002 w
.56427 .63191 m .61017 .64941 L .58247 .64156 L p
.317 0 0 r
F P
s
P
p
.002 w
.58247 .64156 m .5497 .61553 L .56427 .63191 L p
.317 0 0 r
F P
s
P
p
.002 w
.69347 .35427 m .76414 .40317 L .73431 .40693 L p
.708 .892 .964 r
F P
s
P
p
.002 w
.73431 .40693 m .67152 .36958 L .69347 .35427 L p
.708 .892 .964 r
F P
s
P
p
.002 w
.50727 .5555 m .53498 .59898 L .52011 .58227 L p
.759 .292 0 r
F P
s
P
p
.002 w
.52011 .58227 m .51083 .53638 L .50727 .5555 L p
.759 .292 0 r
F P
s
P
p
.002 w
.71562 .33882 m .79402 .39941 L .76414 .40317 L p
.706 .874 .96 r
F P
s
P
p
.002 w
.76414 .40317 m .69347 .35427 L .71562 .33882 L p
.706 .874 .96 r
F P
s
P
p
.002 w
.32074 .84879 m .22628 .70711 L .25612 .70329 L p
.613 .63 .844 r
F P
s
P
p
.002 w
.25612 .70329 m .34021 .83573 L .32074 .84879 L p
.613 .63 .844 r
F P
s
P
p
.002 w
.3083 .32535 m .40374 .2101 L .3886 .19121 L p
.731 .696 .817 r
F P
s
P
p
.002 w
.3886 .19121 m .28049 .31655 L .3083 .32535 L p
.731 .696 .817 r
F P
s
P
p
.002 w
.48638 .92247 m .64393 .95351 L .65865 .96828 L p
.441 .668 .959 r
F P
s
P
p
.002 w
.64393 .95351 m .48638 .92247 L .48934 .90535 L p
.402 .649 .958 r
F P
s
P
p
.002 w
.48638 .92247 m .34021 .83573 L .35985 .82256 L p
.516 .614 .889 r
F P
s
P
p
.002 w
.35985 .82256 m .48934 .90535 L .48638 .92247 L p
.516 .614 .889 r
F P
s
P
p
.002 w
.52291 .47323 m .51443 .51704 L .51807 .49748 L p
.703 .682 .141 r
F P
s
P
p
.002 w
.51807 .49748 m .54361 .45879 L .52291 .47323 L p
.703 .682 .141 r
F P
s
P
p
.002 w
.77437 .63704 m .74674 .68812 L .77367 .69576 L p
.568 .84 .996 r
F P
s
P
p
.002 w
.77367 .69576 m .80552 .63305 L .77437 .63704 L p
.568 .84 .996 r
F P
s
P
p
.002 w
.28049 .31655 m .3886 .19121 L .3733 .17214 L p
.727 .699 .824 r
F P
s
P
p
.002 w
.3733 .17214 m .25255 .30771 L .28049 .31655 L p
.727 .699 .824 r
F P
s
P
p
.002 w
.55463 .63367 m .53498 .59898 L .5497 .61553 L p
.612 .062 0 r
F P
s
P
p
.002 w
.5497 .61553 m .58247 .64156 L .55463 .63367 L p
.612 .062 0 r
F P
s
P
p
.002 w
.7433 .64101 m .71968 .68045 L .74674 .68812 L p
.543 .847 .998 r
F P
s
P
p
.002 w
.74674 .68812 m .77437 .63704 L .7433 .64101 L p
.543 .847 .998 r
F P
s
P
p
.002 w
.5051 .56539 m .51443 .51704 L .51083 .53638 L p
.953 .609 .305 r
F P
s
P
p
.002 w
.51083 .53638 m .52011 .58227 L .5051 .56539 L p
.953 .609 .305 r
F P
s
P
p
.002 w
.34021 .83573 m .25612 .70329 L .28603 .69947 L p
.607 .617 .835 r
F P
s
P
p
.002 w
.28603 .69947 m .35985 .82256 L .34021 .83573 L p
.607 .617 .835 r
F P
s
P
p
.002 w
.54361 .45879 m .51807 .49748 L .52175 .4777 L p
.927 .95 .614 r
F P
s
P
p
.002 w
.52175 .4777 m .56448 .44424 L .54361 .45879 L p
.927 .95 .614 r
F P
s
P
p
.002 w
.7123 .64497 m .6925 .67275 L .71968 .68045 L p
.506 .85 .995 r
F P
s
P
p
.002 w
.71968 .68045 m .7433 .64101 L .7123 .64497 L p
.506 .85 .995 r
F P
s
P
p
.002 w
.48934 .90535 m .62906 .9386 L .64393 .95351 L p
.402 .649 .958 r
F P
s
P
p
.002 w
.62906 .9386 m .48934 .90535 L .49233 .88804 L p
.347 .621 .954 r
F P
s
P
p
.002 w
.48934 .90535 m .35985 .82256 L .37966 .80927 L p
.495 .593 .882 r
F P
s
P
p
.002 w
.37966 .80927 m .49233 .88804 L .48934 .90535 L p
.495 .593 .882 r
F P
s
P
p
.002 w
.52666 .62574 m .52011 .58227 L .53498 .59898 L p
.755 .331 .244 r
F P
s
P
p
.002 w
.53498 .59898 m .55463 .63367 L .52666 .62574 L p
.755 .331 .244 r
F P
s
P
p
.002 w
.68138 .64892 m .66519 .66501 L .6925 .67275 L p
.445 .843 .977 r
F P
s
P
p
.002 w
.6925 .67275 m .7123 .64497 L .68138 .64892 L p
.445 .843 .977 r
F P
s
P
p
.002 w
.35985 .82256 m .28603 .69947 L .31601 .69564 L p
.6 .602 .825 r
F P
s
P
p
.002 w
.31601 .69564 m .37966 .80927 L .35985 .82256 L p
.6 .602 .825 r
F P
s
P
p
.002 w
.52548 .45769 m .58553 .42956 L .56448 .44424 L p
.919 .963 .791 r
F P
s
P
p
.002 w
.56448 .44424 m .52175 .4777 L .52548 .45769 L p
.919 .963 .791 r
F P
s
P
p
.002 w
.48993 .54834 m .51807 .49748 L .51443 .51704 L p
.95 .724 .56 r
F P
s
P
p
.002 w
.51443 .51704 m .5051 .56539 L .48993 .54834 L p
.95 .724 .56 r
F P
s
P
p
.002 w
.78719 .94291 m .65865 .96828 L .64393 .95351 L p
.391 .744 .992 r
F P
s
P
p
.002 w
.65053 .65287 m .63774 .65723 L .66519 .66501 L p
.338 .807 .919 r
F P
s
P
p
.002 w
.66519 .66501 m .68138 .64892 L .65053 .65287 L p
.338 .807 .919 r
F P
s
P
p
.002 w
.49233 .88804 m .61404 .92354 L .62906 .9386 L p
.347 .621 .954 r
F P
s
P
p
.002 w
.61404 .92354 m .49233 .88804 L .49535 .87053 L p
.264 .575 .941 r
F P
s
P
p
.002 w
.49233 .88804 m .37966 .80927 L .39963 .79587 L p
.465 .565 .871 r
F P
s
P
p
.002 w
.39963 .79587 m .49535 .87053 L .49233 .88804 L p
.465 .565 .871 r
F P
s
P
p
.002 w
.49855 .61778 m .5051 .56539 L .52011 .58227 L p
.793 .482 .463 r
F P
s
P
p
.002 w
.52011 .58227 m .52666 .62574 L .49855 .61778 L p
.793 .482 .463 r
F P
s
P
p
.002 w
.37966 .80927 m .31601 .69564 L .34606 .69179 L p
.591 .582 .811 r
F P
s
P
p
.002 w
.34606 .69179 m .39963 .79587 L .37966 .80927 L p
.591 .582 .811 r
F P
s
P
p
.002 w
.61976 .6568 m .61017 .64941 L .63774 .65723 L p
.15 .693 .763 r
F P
s
P
p
.002 w
.63774 .65723 m .65053 .65287 L .61976 .6568 L p
.15 .693 .763 r
F P
s
P
p
.002 w
.52924 .43744 m .60675 .41475 L .58553 .42956 L p
.878 .932 .852 r
F P
s
P
p
.002 w
.58553 .42956 m .52548 .45769 L .52924 .43744 L p
.878 .932 .852 r
F P
s
P
p
.002 w
.64393 .95351 m .75887 .93581 L .78719 .94291 L p
.391 .744 .992 r
F P
s
P
p
.002 w
.75887 .93581 m .64393 .95351 L .62906 .9386 L p
.327 .723 .981 r
F P
s
P
p
.002 w
.58906 .66073 m .58247 .64156 L .61017 .64941 L p
.128 0 0 r
F P
s
P
p
.002 w
.61017 .64941 m .61976 .6568 L .58906 .66073 L p
.128 0 0 r
F P
s
P
p
.002 w
.47461 .53112 m .52175 .4777 L .51807 .49748 L p
.91 .759 .681 r
F P
s
P
p
.002 w
.51807 .49748 m .48993 .54834 L .47461 .53112 L p
.91 .759 .681 r
F P
s
P
p
.002 w
.49535 .87053 m .39963 .79587 L .41978 .78236 L p
.424 .524 .855 r
F P
s
P
p
.002 w
.39963 .79587 m .34606 .69179 L .37619 .68794 L p
.579 .556 .793 r
F P
s
P
p
.002 w
.37619 .68794 m .41978 .78236 L .39963 .79587 L p
.579 .556 .793 r
F P
s
P
p
.002 w
.53306 .41696 m .62816 .39982 L .60675 .41475 L p
.844 .902 .878 r
F P
s
P
p
.002 w
.60675 .41475 m .52924 .43744 L .53306 .41696 L p
.844 .902 .878 r
F P
s
P
p
.002 w
.49535 .87053 m .59887 .90833 L .61404 .92354 L p
.264 .575 .941 r
F P
s
P
p
.002 w
.59887 .90833 m .49535 .87053 L .49841 .85282 L p
.132 .495 .905 r
F P
s
P
p
.002 w
.41978 .78236 m .49841 .85282 L .49535 .87053 L p
.424 .524 .855 r
F P
s
P
p
.002 w
.55843 .66464 m .55463 .63367 L .58247 .64156 L p
.392 0 0 r
F P
s
P
p
.002 w
.58247 .64156 m .58906 .66073 L .55843 .66464 L p
.392 0 0 r
F P
s
P
p
.002 w
.47031 .60977 m .48993 .54834 L .5051 .56539 L p
.794 .564 .589 r
F P
s
P
p
.002 w
.5051 .56539 m .49855 .61778 L .47031 .60977 L p
.794 .564 .589 r
F P
s
P
p
.002 w
.52787 .66855 m .52666 .62574 L .55463 .63367 L p
.546 .173 .284 r
F P
s
P
p
.002 w
.55463 .63367 m .55843 .66464 L .52787 .66855 L p
.546 .173 .284 r
F P
s
P
p
.002 w
.62906 .9386 m .73042 .92868 L .75887 .93581 L p
.327 .723 .981 r
F P
s
P
p
.002 w
.73042 .92868 m .62906 .9386 L .61404 .92354 L p
.23 .682 .951 r
F P
s
P
p
.002 w
.22628 .70711 m .23941 .54433 L .26876 .55265 L p
.678 .656 .821 r
F P
s
P
p
.002 w
.26876 .55265 m .25612 .70329 L .22628 .70711 L p
.678 .656 .821 r
F P
s
P
p
.002 w
.45913 .51372 m .52548 .45769 L .52175 .4777 L p
.873 .769 .745 r
F P
s
P
p
.002 w
.52175 .4777 m .47461 .53112 L .45913 .51372 L p
.873 .769 .745 r
F P
s
P
p
.002 w
.49841 .85282 m .41978 .78236 L .4401 .76873 L p
.363 .462 .828 r
F P
s
P
p
.002 w
.41978 .78236 m .37619 .68794 L .40638 .68408 L p
.562 .521 .767 r
F P
s
P
p
.002 w
.40638 .68408 m .4401 .76873 L .41978 .78236 L p
.562 .521 .767 r
F P
s
P
p
.002 w
.53691 .39624 m .64975 .38477 L .62816 .39982 L p
.817 .878 .89 r
F P
s
P
p
.002 w
.62816 .39982 m .53306 .41696 L .53691 .39624 L p
.817 .878 .89 r
F P
s
P
p
.002 w
.25612 .70329 m .26876 .55265 L .29798 .56093 L p
.678 .647 .812 r
F P
s
P
p
.002 w
.29798 .56093 m .28603 .69947 L .25612 .70329 L p
.678 .647 .812 r
F P
s
P
p
.002 w
.49739 .67245 m .49855 .61778 L .52666 .62574 L p
.617 .341 .476 r
F P
s
P
p
.002 w
.52666 .62574 m .52787 .66855 L .49739 .67245 L p
.617 .341 .476 r
F P
s
P
p
.002 w
.44194 .60173 m .47461 .53112 L .48993 .54834 L p
.785 .611 .664 r
F P
s
P
p
.002 w
.48993 .54834 m .47031 .60977 L .44194 .60173 L p
.785 .611 .664 r
F P
s
P
p
.002 w
.49841 .85282 m .58355 .89296 L .59887 .90833 L p
.132 .495 .905 r
F P
s
P
p
.002 w
.58355 .89296 m .49841 .85282 L .5015 .8349 L p
0 .342 .808 r
F P
s
P
p
.002 w
.4401 .76873 m .5015 .8349 L .49841 .85282 L p
.363 .462 .828 r
F P
s
P
p
.002 w
.28603 .69947 m .29798 .56093 L .32705 .56917 L p
.679 .636 .8 r
F P
s
P
p
.002 w
.32705 .56917 m .31601 .69564 L .28603 .69947 L p
.679 .636 .8 r
F P
s
P
p
.002 w
.31601 .69564 m .32705 .56917 L .35598 .57737 L p
.678 .622 .786 r
F P
s
P
p
.002 w
.35598 .57737 m .34606 .69179 L .31601 .69564 L p
.678 .622 .786 r
F P
s
P
p
.002 w
.46698 .67634 m .47031 .60977 L .49855 .61778 L p
.65 .443 .59 r
F P
s
P
p
.002 w
.49855 .61778 m .49739 .67245 L .46698 .67634 L p
.65 .443 .59 r
F P
s
P
p
.002 w
.61404 .92354 m .70183 .92151 L .73042 .92868 L p
.23 .682 .951 r
F P
s
P
p
.002 w
.70183 .92151 m .61404 .92354 L .59887 .90833 L p
.079 .601 .877 r
F P
s
P
p
.002 w
.5015 .8349 m .4401 .76873 L .4606 .75498 L p
.262 .361 .776 r
F P
s
P
p
.002 w
.4401 .76873 m .40638 .68408 L .43665 .68021 L p
.537 .472 .73 r
F P
s
P
p
.002 w
.43665 .68021 m .4606 .75498 L .4401 .76873 L p
.537 .472 .73 r
F P
s
P
p
.002 w
.34606 .69179 m .35598 .57737 L .38477 .58553 L p
.678 .605 .767 r
F P
s
P
p
.002 w
.38477 .58553 m .37619 .68794 L .34606 .69179 L p
.678 .605 .767 r
F P
s
P
p
.002 w
.54081 .37527 m .67152 .36958 L .64975 .38477 L p
.797 .86 .896 r
F P
s
P
p
.002 w
.64975 .38477 m .53691 .39624 L .54081 .37527 L p
.797 .86 .896 r
F P
s
P
p
.002 w
.44349 .49615 m .52924 .43744 L .52548 .45769 L p
.843 .772 .782 r
F P
s
P
p
.002 w
.52548 .45769 m .45913 .51372 L .44349 .49615 L p
.843 .772 .782 r
F P
s
P
p
.002 w
.37619 .68794 m .38477 .58553 L .41342 .59365 L p
.676 .582 .742 r
F P
s
P
p
.002 w
.41342 .59365 m .40638 .68408 L .37619 .68794 L p
.676 .582 .742 r
F P
s
P
p
.002 w
.43665 .68021 m .44194 .60173 L .47031 .60977 L p
.665 .508 .661 r
F P
s
P
p
.002 w
.47031 .60977 m .46698 .67634 L .43665 .68021 L p
.665 .508 .661 r
F P
s
P
p
.002 w
.41342 .59365 m .45913 .51372 L .47461 .53112 L p
.775 .64 .713 r
F P
s
P
p
.002 w
.47461 .53112 m .44194 .60173 L .41342 .59365 L p
.775 .64 .713 r
F P
s
P
p
.002 w
.40638 .68408 m .41342 .59365 L .44194 .60173 L p
.672 .551 .709 r
F P
s
P
p
.002 w
.44194 .60173 m .43665 .68021 L .40638 .68408 L p
.672 .551 .709 r
F P
s
P
p
.002 w
.5015 .8349 m .56807 .87744 L .58355 .89296 L p
0 .342 .808 r
F P
s
P
p
.002 w
.56807 .87744 m .5015 .8349 L .50463 .81677 L p
0 .051 .553 r
F P
s
P
p
.002 w
.4606 .75498 m .50463 .81677 L .5015 .8349 L p
.262 .361 .776 r
F P
s
P
p
.002 w
.4606 .75498 m .43665 .68021 L .46698 .67634 L p
.498 .399 .673 r
F P
s
P
p
.002 w
.50463 .81677 m .4606 .75498 L .48127 .74111 L p
.082 .176 .664 r
F P
s
P
p
.002 w
.46698 .67634 m .48127 .74111 L .4606 .75498 L p
.498 .399 .673 r
F P
s
P
p
.002 w
.54476 .35405 m .69347 .35427 L .67152 .36958 L p
.782 .846 .9 r
F P
s
P
p
.002 w
.67152 .36958 m .54081 .37527 L .54476 .35405 L p
.782 .846 .9 r
F P
s
P
p
.002 w
.59887 .90833 m .6731 .91431 L .70183 .92151 L p
.079 .601 .877 r
F P
s
P
p
.002 w
.6731 .91431 m .59887 .90833 L .58355 .89296 L p
0 .44 .709 r
F P
s
P
p
.002 w
.48127 .74111 m .46698 .67634 L .49739 .67245 L p
.432 .283 .577 r
F P
s
P
p
.002 w
.49739 .67245 m .50213 .72712 L .48127 .74111 L p
.432 .283 .577 r
F P
s
P
p
.002 w
.50213 .72712 m .49739 .67245 L .52787 .66855 L p
.312 .086 .402 r
F P
s
P
p
.002 w
.45913 .51372 m .41342 .59365 L .38477 .58553 L p
.765 .66 .747 r
F P
s
P
p
.002 w
.4277 .47839 m .53306 .41696 L .52924 .43744 L p
.821 .771 .806 r
F P
s
P
p
.002 w
.52924 .43744 m .44349 .49615 L .4277 .47839 L p
.821 .771 .806 r
F P
s
P
p
.002 w
.52787 .66855 m .52317 .71301 L .50213 .72712 L p
.312 .086 .402 r
F P
s
P
p
.002 w
.52317 .71301 m .52787 .66855 L .55843 .66464 L p
.085 0 .079 r
F P
s
P
p
.002 w
.38477 .58553 m .44349 .49615 L .45913 .51372 L p
.765 .66 .747 r
F P
s
P
p
.002 w
.55843 .66464 m .54439 .69877 L .52317 .71301 L p
.085 0 .079 r
F P
s
P
p
.002 w
.54439 .69877 m .55843 .66464 L .58906 .66073 L p
0 0 0 r
F P
s
P
p
.002 w
.48127 .74111 m .5078 .79843 L .50463 .81677 L p
.082 .176 .664 r
F P
s
P
p
.002 w
.5078 .79843 m .48127 .74111 L .50213 .72712 L p
0 0 .391 r
F P
s
P
p
.002 w
.54876 .33258 m .71562 .33882 L .69347 .35427 L p
.769 .834 .902 r
F P
s
P
p
.002 w
.69347 .35427 m .54476 .35405 L .54876 .33258 L p
.769 .834 .902 r
F P
s
P
p
.002 w
.44349 .49615 m .38477 .58553 L .35598 .57737 L p
.756 .674 .771 r
F P
s
P
p
.002 w
.50463 .81677 m .55243 .86176 L .56807 .87744 L p
0 .051 .553 r
F P
s
P
p
.002 w
.55243 .86176 m .50463 .81677 L .5078 .79843 L p
.808 .343 0 r
F P
s
P
p
.002 w
.58906 .66073 m .5658 .6844 L .54439 .69877 L p
0 0 0 r
F P
s
P
p
.002 w
.5658 .6844 m .58906 .66073 L .61976 .6568 L p
.509 .898 .743 r
F P
s
P
p
.002 w
.58355 .89296 m .64424 .90708 L .6731 .91431 L p
0 .44 .709 r
F P
s
P
p
.002 w
.64424 .90708 m .58355 .89296 L .56807 .87744 L p
.424 0 0 r
F P
s
P
p
.002 w
.61976 .6568 m .5874 .66991 L .5658 .6844 L p
.509 .898 .743 r
F P
s
P
p
.002 w
.5874 .66991 m .61976 .6568 L .65053 .65287 L p
.626 .959 .897 r
F P
s
P
p
.002 w
.65357 1.21702 m .48959 1.18197 L .48703 1.19813 L p
.416 .68 .971 r
F P
s
P
p
.002 w
.48703 1.19813 m .66934 1.23095 L .65357 1.21702 L p
.416 .68 .971 r
F P
s
P
p
.002 w
.3162 1.10652 m .48703 1.19813 L .48959 1.18197 L p
.519 .645 .913 r
F P
s
P
p
.002 w
.4277 .47839 m .35598 .57737 L .32705 .56917 L p
.748 .684 .789 r
F P
s
P
p
.002 w
.35598 .57737 m .4277 .47839 L .44349 .49615 L p
.756 .674 .771 r
F P
s
P
p
.002 w
.65053 .65287 m .6092 .65529 L .5874 .66991 L p
.626 .959 .897 r
F P
s
P
p
.002 w
.6092 .65529 m .65053 .65287 L .68138 .64892 L p
.668 .953 .95 r
F P
s
P
p
.002 w
.41174 .46045 m .53691 .39624 L .53306 .41696 L p
.803 .77 .823 r
F P
s
P
p
.002 w
.53306 .41696 m .4277 .47839 L .41174 .46045 L p
.803 .77 .823 r
F P
s
P
p
.002 w
.50213 .72712 m .51101 .77988 L .5078 .79843 L p
0 0 .391 r
F P
s
P
p
.002 w
.51101 .77988 m .50213 .72712 L .52317 .71301 L p
.709 .682 .139 r
F P
s
P
p
.002 w
.68138 .64892 m .63119 .64054 L .6092 .65529 L p
.668 .953 .95 r
F P
s
P
p
.002 w
.63119 .64054 m .68138 .64892 L .7123 .64497 L p
.684 .933 .968 r
F P
s
P
p
.002 w
.41174 .46045 m .32705 .56917 L .29798 .56093 L p
.741 .692 .804 r
F P
s
P
p
.002 w
.32705 .56917 m .41174 .46045 L .4277 .47839 L p
.748 .684 .789 r
F P
s
P
p
.002 w
.7123 .64497 m .65338 .62566 L .63119 .64054 L p
.684 .933 .968 r
F P
s
P
p
.002 w
.65338 .62566 m .7123 .64497 L .7433 .64101 L p
.689 .912 .972 r
F P
s
P
p
.002 w
.56807 .87744 m .61523 .89981 L .64424 .90708 L p
.424 0 0 r
F P
s
P
p
.002 w
.61523 .89981 m .56807 .87744 L .55243 .86176 L p
.647 .123 0 r
F P
s
P
p
.002 w
.5078 .79843 m .53663 .84592 L .55243 .86176 L p
.808 .343 0 r
F P
s
P
p
.002 w
.53663 .84592 m .5078 .79843 L .51101 .77988 L p
.951 .611 .326 r
F P
s
P
p
.002 w
.7433 .64101 m .67577 .61064 L .65338 .62566 L p
.689 .912 .972 r
F P
s
P
p
.002 w
.67577 .61064 m .7433 .64101 L .77437 .63704 L p
.69 .895 .971 r
F P
s
P
p
.002 w
.39561 .44232 m .29798 .56093 L .26876 .55265 L p
.735 .698 .815 r
F P
s
P
p
.002 w
.29798 .56093 m .39561 .44232 L .41174 .46045 L p
.741 .692 .804 r
F P
s
P
p
.002 w
.48959 1.18197 m .33609 1.09385 L .3162 1.10652 L p
.519 .645 .913 r
F P
s
P
p
.002 w
.21494 .96521 m .3162 1.10652 L .33609 1.09385 L p
.605 .644 .863 r
F P
s
P
p
.002 w
.77437 .63704 m .69836 .59548 L .67577 .61064 L p
.69 .895 .971 r
F P
s
P
p
.002 w
.69836 .59548 m .77437 .63704 L .80552 .63305 L p
.69 .879 .969 r
F P
s
P
p
.002 w
.63763 1.20296 m .49218 1.16562 L .48959 1.18197 L p
.38 .662 .969 r
F P
s
P
p
.002 w
.48959 1.18197 m .65357 1.21702 L .63763 1.20296 L p
.38 .662 .969 r
F P
s
P
p
.002 w
.33609 1.09385 m .48959 1.18197 L .49218 1.16562 L p
.504 .631 .909 r
F P
s
P
p
.002 w
.52317 .71301 m .51425 .7611 L .51101 .77988 L p
.709 .682 .139 r
F P
s
P
p
.002 w
.51425 .7611 m .52317 .71301 L .54439 .69877 L p
.917 .943 .589 r
F P
s
P
p
.002 w
.80552 .63305 m .72116 .58019 L .69836 .59548 L p
.69 .879 .969 r
F P
s
P
p
.002 w
.37931 .424 m .26876 .55265 L .23941 .54433 L p
.73 .702 .824 r
F P
s
P
p
.002 w
.26876 .55265 m .37931 .424 L .39561 .44232 L p
.735 .698 .815 r
F P
s
P
p
.002 w
.39561 .44232 m .54081 .37527 L .53691 .39624 L p
.788 .768 .835 r
F P
s
P
p
.002 w
.53691 .39624 m .41174 .46045 L .39561 .44232 L p
.788 .768 .835 r
F P
s
P
p
.002 w
.81799 .88833 m .78719 .94291 L .75887 .93581 L p
.482 .847 .991 r
F P
s
P
p
.002 w
.55243 .86176 m .58609 .8925 L .61523 .89981 L p
.647 .123 0 r
F P
s
P
p
.002 w
.58609 .8925 m .55243 .86176 L .53663 .84592 L p
.754 .346 .277 r
F P
s
P
p
.002 w
.49218 1.16562 m .35616 1.08106 L .33609 1.09385 L p
.504 .631 .909 r
F P
s
P
p
.002 w
.24593 .96126 m .33609 1.09385 L .35616 1.08106 L p
.6 .633 .855 r
F P
s
P
p
.002 w
.33609 1.09385 m .24593 .96126 L .21494 .96521 L p
.605 .644 .863 r
F P
s
P
p
.002 w
.51101 .77988 m .52067 .82991 L .53663 .84592 L p
.951 .611 .326 r
F P
s
P
p
.002 w
.52067 .82991 m .51101 .77988 L .51425 .7611 L p
.949 .72 .558 r
F P
s
P
p
.002 w
.23941 .54433 m .36285 .40549 L .37931 .424 L p
.73 .702 .824 r
F P
s
P
p
.002 w
.75887 .93581 m .78547 .89248 L .81799 .88833 L p
.482 .847 .991 r
F P
s
P
p
.002 w
.78547 .89248 m .75887 .93581 L .73042 .92868 L p
.431 .839 .972 r
F P
s
P
p
.002 w
.54439 .69877 m .51753 .7421 L .51425 .7611 L p
.917 .943 .589 r
F P
s
P
p
.002 w
.51753 .7421 m .54439 .69877 L .5658 .6844 L p
.918 .972 .777 r
F P
s
P
p
.002 w
.62152 1.18875 m .4948 1.14907 L .49218 1.16562 L p
.332 .637 .964 r
F P
s
P
p
.002 w
.49218 1.16562 m .63763 1.20296 L .62152 1.18875 L p
.332 .637 .964 r
F P
s
P
p
.002 w
.35616 1.08106 m .49218 1.16562 L .4948 1.14907 L p
.485 .613 .903 r
F P
s
P
p
.002 w
.73042 .92868 m .75305 .89661 L .78547 .89248 L p
.431 .839 .972 r
F P
s
P
p
.002 w
.75305 .89661 m .73042 .92868 L .70183 .92151 L p
.35 .812 .928 r
F P
s
P
p
.002 w
.37931 .424 m .54476 .35405 L .54081 .37527 L p
.776 .767 .844 r
F P
s
P
p
.002 w
.54081 .37527 m .39561 .44232 L .37931 .424 L p
.776 .767 .844 r
F P
s
P
p
.002 w
.53663 .84592 m .55681 .88516 L .58609 .8925 L p
.754 .346 .277 r
F P
s
P
p
.002 w
.55681 .88516 m .53663 .84592 L .52067 .82991 L p
.788 .481 .469 r
F P
s
P
p
.002 w
.4948 1.14907 m .37641 1.06816 L .35616 1.08106 L p
.485 .613 .903 r
F P
s
P
p
.002 w
.27699 .9573 m .35616 1.08106 L .37641 1.06816 L p
.594 .619 .846 r
F P
s
P
p
.002 w
.35616 1.08106 m .27699 .9573 L .24593 .96126 L p
.6 .633 .855 r
F P
s
P
p
.002 w
.70183 .92151 m .72071 .90073 L .75305 .89661 L p
.35 .812 .928 r
F P
s
P
p
.002 w
.72071 .90073 m .70183 .92151 L .6731 .91431 L p
.219 .741 .828 r
F P
s
P
p
.002 w
.5658 .6844 m .52085 .72287 L .51753 .7421 L p
.918 .972 .777 r
F P
s
P
p
.002 w
.52085 .72287 m .5658 .6844 L .5874 .66991 L p
.881 .949 .85 r
F P
s
P
p
.002 w
.51425 .7611 m .50454 .81374 L .52067 .82991 L p
.949 .72 .558 r
F P
s
P
p
.002 w
.50454 .81374 m .51425 .7611 L .51753 .7421 L p
.913 .76 .677 r
F P
s
P
p
.002 w
.77206 1.2064 m .65357 1.21702 L .66934 1.23095 L p
.355 .743 .986 r
F P
s
P
p
.002 w
.66934 1.23095 m .80189 1.2129 L .77206 1.2064 L p
.355 .743 .986 r
F P
s
P
p
.002 w
.6731 .91431 m .68846 .90484 L .72071 .90073 L p
.219 .741 .828 r
F P
s
P
p
.002 w
.68846 .90484 m .6731 .91431 L .64424 .90708 L p
0 0 0 r
F P
s
P
p
.002 w
.60525 1.17439 m .49745 1.13233 L .4948 1.14907 L p
.264 .6 .952 r
F P
s
P
p
.002 w
.4948 1.14907 m .62152 1.18875 L .60525 1.17439 L p
.264 .6 .952 r
F P
s
P
p
.002 w
.37641 1.06816 m .4948 1.14907 L .49745 1.13233 L p
.46 .589 .895 r
F P
s
P
p
.002 w
.49745 1.13233 m .39684 1.05515 L .37641 1.06816 L p
.46 .589 .895 r
F P
s
P
p
.002 w
.30814 .95333 m .37641 1.06816 L .39684 1.05515 L p
.587 .601 .834 r
F P
s
P
p
.002 w
.37641 1.06816 m .30814 .95333 L .27699 .9573 L p
.594 .619 .846 r
F P
s
P
p
.002 w
.52067 .82991 m .52739 .87779 L .55681 .88516 L p
.788 .481 .469 r
F P
s
P
p
.002 w
.52739 .87779 m .52067 .82991 L .50454 .81374 L p
.791 .56 .587 r
F P
s
P
p
.002 w
.36285 .40549 m .54876 .33258 L .54476 .35405 L p
.767 .765 .851 r
F P
s
P
p
.002 w
.54476 .35405 m .37931 .424 L .36285 .40549 L p
.767 .765 .851 r
F P
s
P
p
.002 w
.5874 .66991 m .52421 .70341 L .52085 .72287 L p
.881 .949 .85 r
F P
s
P
p
.002 w
.52421 .70341 m .5874 .66991 L .6092 .65529 L p
.847 .921 .881 r
F P
s
P
p
.002 w
.64424 .90708 m .65629 .90894 L .68846 .90484 L p
0 0 0 r
F P
s
P
p
.002 w
.65629 .90894 m .64424 .90708 L .61523 .89981 L p
.224 0 0 r
F P
s
P
p
.002 w
.51753 .7421 m .48824 .7974 L .50454 .81374 L p
.913 .76 .677 r
F P
s
P
p
.002 w
.48824 .7974 m .51753 .7421 L .52085 .72287 L p
.878 .774 .744 r
F P
s
P
p
.002 w
.74208 1.19986 m .63763 1.20296 L .65357 1.21702 L p
.296 .721 .971 r
F P
s
P
p
.002 w
.65357 1.21702 m .77206 1.2064 L .74208 1.19986 L p
.296 .721 .971 r
F P
s
P
p
.002 w
.61523 .89981 m .62422 .91303 L .65629 .90894 L p
.224 0 0 r
F P
s
P
p
.002 w
.62422 .91303 m .61523 .89981 L .58609 .8925 L p
.428 0 .043 r
F P
s
P
p
.002 w
.50013 1.11538 m .41746 1.04201 L .39684 1.05515 L p
.428 .556 .883 r
F P
s
P
p
.002 w
.39684 1.05515 m .49745 1.13233 L .50013 1.11538 L p
.428 .556 .883 r
F P
s
P
p
.002 w
.33936 .94935 m .39684 1.05515 L .41746 1.04201 L p
.577 .58 .819 r
F P
s
P
p
.002 w
.39684 1.05515 m .33936 .94935 L .30814 .95333 L p
.587 .601 .834 r
F P
s
P
p
.002 w
.5888 1.15988 m .50013 1.11538 L .49745 1.13233 L p
.163 .54 .925 r
F P
s
P
p
.002 w
.49745 1.13233 m .60525 1.17439 L .5888 1.15988 L p
.163 .54 .925 r
F P
s
P
p
.002 w
.6092 .65529 m .52762 .68372 L .52421 .70341 L p
.847 .921 .881 r
F P
s
P
p
.002 w
.52762 .68372 m .6092 .65529 L .63119 .64054 L p
.82 .899 .897 r
F P
s
P
p
.002 w
.50454 .81374 m .49782 .87038 L .52739 .87779 L p
.791 .56 .587 r
F P
s
P
p
.002 w
.49782 .87038 m .50454 .81374 L .48824 .7974 L p
.784 .609 .662 r
F P
s
P
p
.002 w
.58609 .8925 m .59223 .91711 L .62422 .91303 L p
.428 0 .043 r
F P
s
P
p
.002 w
.59223 .91711 m .58609 .8925 L .55681 .88516 L p
.551 .19 .307 r
F P
s
P
p
.002 w
.22516 .80203 m .21494 .96521 L .24593 .96126 L p
.672 .669 .838 r
F P
s
P
p
.002 w
.55681 .88516 m .56032 .92118 L .59223 .91711 L p
.551 .19 .307 r
F P
s
P
p
.002 w
.56032 .92118 m .55681 .88516 L .52739 .87779 L p
.614 .34 .479 r
F P
s
P
p
.002 w
.71196 1.1933 m .62152 1.18875 L .63763 1.20296 L p
.215 .684 .941 r
F P
s
P
p
.002 w
.63763 1.20296 m .74208 1.19986 L .71196 1.1933 L p
.215 .684 .941 r
F P
s
P
p
.002 w
.50285 1.09823 m .43827 1.02876 L .41746 1.04201 L p
.381 .511 .863 r
F P
s
P
p
.002 w
.41746 1.04201 m .50013 1.11538 L .50285 1.09823 L p
.381 .511 .863 r
F P
s
P
p
.002 w
.37068 .94536 m .41746 1.04201 L .43827 1.02876 L p
.565 .552 .799 r
F P
s
P
p
.002 w
.41746 1.04201 m .37068 .94536 L .33936 .94935 L p
.577 .58 .819 r
F P
s
P
p
.002 w
.52085 .72287 m .47177 .78089 L .48824 .7974 L p
.878 .774 .744 r
F P
s
P
p
.002 w
.47177 .78089 m .52085 .72287 L .52421 .70341 L p
.849 .78 .784 r
F P
s
P
p
.002 w
.25606 .80978 m .24593 .96126 L .27699 .9573 L p
.673 .661 .83 r
F P
s
P
p
.002 w
.24593 .96126 m .25606 .80978 L .22516 .80203 L p
.672 .669 .838 r
F P
s
P
p
.002 w
.63119 .64054 m .53106 .66378 L .52762 .68372 L p
.82 .899 .897 r
F P
s
P
p
.002 w
.53106 .66378 m .63119 .64054 L .65338 .62566 L p
.799 .88 .906 r
F P
s
P
p
.002 w
.52739 .87779 m .5285 .92524 L .56032 .92118 L p
.614 .34 .479 r
F P
s
P
p
.002 w
.5285 .92524 m .52739 .87779 L .49782 .87038 L p
.645 .438 .588 r
F P
s
P
p
.002 w
.2868 .81748 m .27699 .9573 L .30814 .95333 L p
.673 .651 .819 r
F P
s
P
p
.002 w
.27699 .9573 m .2868 .81748 L .25606 .80978 L p
.673 .661 .83 r
F P
s
P
p
.002 w
.48824 .7974 m .46812 .86293 L .49782 .87038 L p
.784 .609 .662 r
F P
s
P
p
.002 w
.46812 .86293 m .48824 .7974 L .47177 .78089 L p
.775 .641 .713 r
F P
s
P
p
.002 w
.57219 1.14521 m .50285 1.09823 L .50013 1.11538 L p
.008 .438 .865 r
F P
s
P
p
.002 w
.50013 1.11538 m .5888 1.15988 L .57219 1.14521 L p
.008 .438 .865 r
F P
s
P
p
.002 w
.31739 .82515 m .30814 .95333 L .33936 .94935 L p
.674 .639 .807 r
F P
s
P
p
.002 w
.30814 .95333 m .31739 .82515 L .2868 .81748 L p
.673 .651 .819 r
F P
s
P
p
.002 w
.49782 .87038 m .49677 .92928 L .5285 .92524 L p
.645 .438 .588 r
F P
s
P
p
.002 w
.49677 .92928 m .49782 .87038 L .46812 .86293 L p
.66 .503 .66 r
F P
s
P
p
.002 w
.34783 .83278 m .33936 .94935 L .37068 .94536 L p
.674 .624 .791 r
F P
s
P
p
.002 w
.33936 .94935 m .34783 .83278 L .31739 .82515 L p
.674 .639 .807 r
F P
s
P
p
.002 w
.5056 1.08086 m .45927 1.01538 L .43827 1.02876 L p
.313 .441 .831 r
F P
s
P
p
.002 w
.43827 1.02876 m .50285 1.09823 L .5056 1.08086 L p
.313 .441 .831 r
F P
s
P
p
.002 w
.40207 .94135 m .43827 1.02876 L .45927 1.01538 L p
.547 .515 .771 r
F P
s
P
p
.002 w
.43827 1.02876 m .40207 .94135 L .37068 .94536 L p
.565 .552 .799 r
F P
s
P
p
.002 w
.6817 1.1867 m .60525 1.17439 L .62152 1.18875 L p
.097 .62 .88 r
F P
s
P
p
.002 w
.62152 1.18875 m .71196 1.1933 L .6817 1.1867 L p
.097 .62 .88 r
F P
s
P
p
.002 w
.65338 .62566 m .53455 .64359 L .53106 .66378 L p
.799 .88 .906 r
F P
s
P
p
.002 w
.53455 .64359 m .65338 .62566 L .67577 .61064 L p
.782 .866 .911 r
F P
s
P
p
.002 w
.37812 .84037 m .37068 .94536 L .40207 .94135 L p
.673 .605 .771 r
F P
s
P
p
.002 w
.37068 .94536 m .37812 .84037 L .34783 .83278 L p
.674 .624 .791 r
F P
s
P
p
.002 w
.52421 .70341 m .45513 .7642 L .47177 .78089 L p
.849 .78 .784 r
F P
s
P
p
.002 w
.45513 .7642 m .52421 .70341 L .52762 .68372 L p
.826 .781 .81 r
F P
s
P
p
.002 w
.46812 .86293 m .46512 .93332 L .49677 .92928 L p
.66 .503 .66 r
F P
s
P
p
.002 w
.46512 .93332 m .46812 .86293 L .43826 .85545 L p
.668 .548 .709 r
F P
s
P
p
.002 w
.47177 .78089 m .43826 .85545 L .46812 .86293 L p
.775 .641 .713 r
F P
s
P
p
.002 w
.43826 .85545 m .47177 .78089 L .45513 .7642 L p
.765 .662 .748 r
F P
s
P
p
.002 w
.45927 1.01538 m .43355 .93734 L .40207 .94135 L p
.547 .515 .771 r
F P
s
P
p
.002 w
.40826 .84793 m .40207 .94135 L .43355 .93734 L p
.672 .58 .745 r
F P
s
P
p
.002 w
.40207 .94135 m .40826 .84793 L .37812 .84037 L p
.673 .605 .771 r
F P
s
P
p
.002 w
.55539 1.13039 m .5056 1.08086 L .50285 1.09823 L p
0 .255 .724 r
F P
s
P
p
.002 w
.50285 1.09823 m .57219 1.14521 L .55539 1.13039 L p
0 .255 .724 r
F P
s
P
p
.002 w
.48046 1.00188 m .46512 .93332 L .43355 .93734 L p
.522 .463 .731 r
F P
s
P
p
.002 w
.43355 .93734 m .45927 1.01538 L .48046 1.00188 L p
.522 .463 .731 r
F P
s
P
p
.002 w
.43826 .85545 m .43355 .93734 L .46512 .93332 L p
.668 .548 .709 r
F P
s
P
p
.002 w
.43355 .93734 m .43826 .85545 L .40826 .84793 L p
.672 .58 .745 r
F P
s
P
p
.002 w
.45513 .7642 m .40826 .84793 L .43826 .85545 L p
.765 .662 .748 r
F P
s
P
p
.002 w
.50838 1.06329 m .48046 1.00188 L .45927 1.01538 L p
.202 .329 .771 r
F P
s
P
p
.002 w
.45927 1.01538 m .5056 1.08086 L .50838 1.06329 L p
.202 .329 .771 r
F P
s
P
p
.002 w
.50185 .98826 m .49677 .92928 L .46512 .93332 L p
.483 .387 .671 r
F P
s
P
p
.002 w
.46512 .93332 m .48046 1.00188 L .50185 .98826 L p
.483 .387 .671 r
F P
s
P
p
.002 w
.65129 1.18007 m .5888 1.15988 L .60525 1.17439 L p
0 .506 .762 r
F P
s
P
p
.002 w
.60525 1.17439 m .6817 1.1867 L .65129 1.18007 L p
0 .506 .762 r
F P
s
P
p
.002 w
.67577 .61064 m .53807 .62316 L .53455 .64359 L p
.782 .866 .911 r
F P
s
P
p
.002 w
.53807 .62316 m .67577 .61064 L .69836 .59548 L p
.769 .854 .914 r
F P
s
P
p
.002 w
.52344 .9745 m .5285 .92524 L .49677 .92928 L p
.419 .272 .573 r
F P
s
P
p
.002 w
.49677 .92928 m .50185 .98826 L .52344 .9745 L p
.419 .272 .573 r
F P
s
P
p
.002 w
.40826 .84793 m .45513 .7642 L .43832 .74734 L p
.756 .677 .774 r
F P
s
P
p
.002 w
.43832 .74734 m .37812 .84037 L .40826 .84793 L p
.756 .677 .774 r
F P
s
P
p
.002 w
.52762 .68372 m .43832 .74734 L .45513 .7642 L p
.826 .781 .81 r
F P
s
P
p
.002 w
.43832 .74734 m .52762 .68372 L .53106 .66378 L p
.807 .781 .829 r
F P
s
P
p
.002 w
.54523 .96062 m .56032 .92118 L .5285 .92524 L p
.308 .086 .405 r
F P
s
P
p
.002 w
.5285 .92524 m .52344 .9745 L .54523 .96062 L p
.308 .086 .405 r
F P
s
P
p
.002 w
.37812 .84037 m .43832 .74734 L .42132 .73029 L p
.748 .689 .794 r
F P
s
P
p
.002 w
.42132 .73029 m .34783 .83278 L .37812 .84037 L p
.748 .689 .794 r
F P
s
P
p
.002 w
.56723 .94661 m .59223 .91711 L .56032 .92118 L p
.114 0 .118 r
F P
s
P
p
.002 w
.56032 .92118 m .54523 .96062 L .56723 .94661 L p
.114 0 .118 r
F P
s
P
p
.002 w
.5112 1.0455 m .50185 .98826 L .48046 1.00188 L p
.012 .131 .643 r
F P
s
P
p
.002 w
.48046 1.00188 m .50838 1.06329 L .5112 1.0455 L p
.012 .131 .643 r
F P
s
P
p
.002 w
.5056 1.08086 m .55539 1.13039 L .53842 1.11542 L p
.564 .044 0 r
F P
s
P
p
.002 w
.53842 1.11542 m .50838 1.06329 L .5056 1.08086 L p
.564 .044 0 r
F P
s
P
p
.002 w
.69836 .59548 m .54165 .60247 L .53807 .62316 L p
.769 .854 .914 r
F P
s
P
p
.002 w
.54165 .60247 m .69836 .59548 L .72116 .58019 L p
.758 .844 .916 r
F P
s
P
p
.002 w
.58943 .93247 m .62422 .91303 L .59223 .91711 L p
0 0 0 r
F P
s
P
p
.002 w
.59223 .91711 m .56723 .94661 L .58943 .93247 L p
0 0 0 r
F P
s
P
p
.002 w
.5888 1.15988 m .65129 1.18007 L .62074 1.1734 L p
.285 0 0 r
F P
s
P
p
.002 w
.62074 1.1734 m .57219 1.14521 L .5888 1.15988 L p
.285 0 0 r
F P
s
P
p
.002 w
.34783 .83278 m .42132 .73029 L .40414 .71306 L p
.741 .698 .81 r
F P
s
P
p
.002 w
.40414 .71306 m .31739 .82515 L .34783 .83278 L p
.741 .698 .81 r
F P
s
P
p
.002 w
.62422 .91303 m .58943 .93247 L .61184 .91819 L p
.414 .829 .645 r
F P
s
P
p
.002 w
.61184 .91819 m .65629 .90894 L .62422 .91303 L p
.414 .829 .645 r
F P
s
P
p
.002 w
.49092 1.48006 m .3113 1.38718 L .33164 1.37508 L p
.465 .629 .924 r
F P
s
P
p
.002 w
.33164 1.37508 m .49309 1.46488 L .49092 1.48006 L p
.465 .629 .924 r
F P
s
P
p
.002 w
.53106 .66378 m .42132 .73029 L .43832 .74734 L p
.807 .781 .829 r
F P
s
P
p
.002 w
.63447 .90378 m .68846 .90484 L .65629 .90894 L p
.555 .933 .84 r
F P
s
P
p
.002 w
.65629 .90894 m .61184 .91819 L .63447 .90378 L p
.555 .933 .84 r
F P
s
P
p
.002 w
.42132 .73029 m .53106 .66378 L .53455 .64359 L p
.792 .78 .842 r
F P
s
P
p
.002 w
.51405 1.02748 m .52344 .9745 L .50185 .98826 L p
0 0 .358 r
F P
s
P
p
.002 w
.50185 .98826 m .5112 1.0455 L .51405 1.02748 L p
0 0 .358 r
F P
s
P
p
.002 w
.31739 .82515 m .40414 .71306 L .38677 .69565 L p
.735 .705 .822 r
F P
s
P
p
.002 w
.38677 .69565 m .2868 .81748 L .31739 .82515 L p
.735 .705 .822 r
F P
s
P
p
.002 w
.72116 .58019 m .54527 .58152 L .54165 .60247 L p
.758 .844 .916 r
F P
s
P
p
.002 w
.65732 .88923 m .72071 .90073 L .68846 .90484 L p
.619 .952 .923 r
F P
s
P
p
.002 w
.68846 .90484 m .63447 .90378 L .65732 .88923 L p
.619 .952 .923 r
F P
s
P
p
.002 w
.59003 1.16671 m .55539 1.13039 L .57219 1.14521 L p
.508 0 0 r
F P
s
P
p
.002 w
.57219 1.14521 m .62074 1.1734 L .59003 1.16671 L p
.508 0 0 r
F P
s
P
p
.002 w
.68038 .87453 m .75305 .89661 L .72071 .90073 L p
.647 .944 .958 r
F P
s
P
p
.002 w
.72071 .90073 m .65732 .88923 L .68038 .87453 L p
.647 .944 .958 r
F P
s
P
p
.002 w
.50838 1.06329 m .53842 1.11542 L .52127 1.10028 L p
.843 .385 0 r
F P
s
P
p
.002 w
.52127 1.10028 m .5112 1.0455 L .50838 1.06329 L p
.843 .385 0 r
F P
s
P
p
.002 w
.2868 .81748 m .38677 .69565 L .36922 .67805 L p
.73 .71 .832 r
F P
s
P
p
.002 w
.36922 .67805 m .25606 .80978 L .2868 .81748 L p
.73 .71 .832 r
F P
s
P
p
.002 w
.3113 1.38718 m .20266 1.24666 L .23487 1.24265 L p
.584 .635 .868 r
F P
s
P
p
.002 w
.23487 1.24265 m .33164 1.37508 L .3113 1.38718 L p
.584 .635 .868 r
F P
s
P
p
.002 w
.70366 .8597 m .78547 .89248 L .75305 .89661 L p
.659 .929 .972 r
F P
s
P
p
.002 w
.75305 .89661 m .68038 .87453 L .70366 .8597 L p
.659 .929 .972 r
F P
s
P
p
.002 w
.49309 1.46488 m .33164 1.37508 L .35217 1.36287 L p
.444 .608 .917 r
F P
s
P
p
.002 w
.35217 1.36287 m .49528 1.44952 L .49309 1.46488 L p
.444 .608 .917 r
F P
s
P
p
.002 w
.53455 .64359 m .40414 .71306 L .42132 .73029 L p
.792 .78 .842 r
F P
s
P
p
.002 w
.52344 .9745 m .51405 1.02748 L .51694 1.00924 L p
.716 .681 .138 r
F P
s
P
p
.002 w
.51694 1.00924 m .54523 .96062 L .52344 .9745 L p
.716 .681 .138 r
F P
s
P
p
.002 w
.72718 .84472 m .81799 .88833 L .78547 .89248 L p
.665 .914 .977 r
F P
s
P
p
.002 w
.78547 .89248 m .70366 .8597 L .72718 .84472 L p
.665 .914 .977 r
F P
s
P
p
.002 w
.25606 .80978 m .36922 .67805 L .35148 .66026 L p
.725 .715 .84 r
F P
s
P
p
.002 w
.35148 .66026 m .22516 .80203 L .25606 .80978 L p
.725 .715 .84 r
F P
s
P
p
.002 w
.40414 .71306 m .53455 .64359 L .53807 .62316 L p
.779 .779 .853 r
F P
s
P
p
.002 w
.79757 1.17262 m .77206 1.2064 L .80189 1.2129 L p
.441 .839 .979 r
F P
s
P
p
.002 w
.80189 1.2129 m .83158 1.16839 L .79757 1.17262 L p
.441 .839 .979 r
F P
s
P
p
.002 w
.55918 1.15998 m .53842 1.11542 L .55539 1.13039 L p
.67 .173 .058 r
F P
s
P
p
.002 w
.55539 1.13039 m .59003 1.16671 L .55918 1.15998 L p
.67 .173 .058 r
F P
s
P
p
.002 w
.33164 1.37508 m .23487 1.24265 L .26719 1.23863 L p
.578 .62 .857 r
F P
s
P
p
.002 w
.26719 1.23863 m .35217 1.36287 L .33164 1.37508 L p
.578 .62 .857 r
F P
s
P
p
.002 w
.50393 1.08498 m .51405 1.02748 L .5112 1.0455 L p
.948 .611 .346 r
F P
s
P
p
.002 w
.5112 1.0455 m .52127 1.10028 L .50393 1.08498 L p
.948 .611 .346 r
F P
s
P
p
.002 w
.54523 .96062 m .51694 1.00924 L .51987 .99077 L p
.905 .933 .561 r
F P
s
P
p
.002 w
.51987 .99077 m .56723 .94661 L .54523 .96062 L p
.905 .933 .561 r
F P
s
P
p
.002 w
.76367 1.17684 m .74208 1.19986 L .77206 1.2064 L p
.395 .827 .957 r
F P
s
P
p
.002 w
.77206 1.2064 m .79757 1.17262 L .76367 1.17684 L p
.395 .827 .957 r
F P
s
P
p
.002 w
.53807 .62316 m .38677 .69565 L .40414 .71306 L p
.779 .779 .853 r
F P
s
P
p
.002 w
.49528 1.44952 m .35217 1.36287 L .3729 1.35054 L p
.416 .581 .907 r
F P
s
P
p
.002 w
.3729 1.35054 m .4975 1.43396 L .49528 1.44952 L p
.416 .581 .907 r
F P
s
P
p
.002 w
.72986 1.18105 m .71196 1.1933 L .74208 1.19986 L p
.326 .8 .915 r
F P
s
P
p
.002 w
.74208 1.19986 m .76367 1.17684 L .72986 1.18105 L p
.326 .8 .915 r
F P
s
P
p
.002 w
.35217 1.36287 m .26719 1.23863 L .29959 1.23459 L p
.57 .601 .844 r
F P
s
P
p
.002 w
.29959 1.23459 m .3729 1.35054 L .35217 1.36287 L p
.57 .601 .844 r
F P
s
P
p
.002 w
.38677 .69565 m .53807 .62316 L .54165 .60247 L p
.769 .778 .861 r
F P
s
P
p
.002 w
.52818 1.15322 m .52127 1.10028 L .53842 1.11542 L p
.753 .358 .305 r
F P
s
P
p
.002 w
.53842 1.11542 m .55918 1.15998 L .52818 1.15322 L p
.753 .358 .305 r
F P
s
P
p
.002 w
.52283 .97207 m .58943 .93247 L .56723 .94661 L p
.914 .98 .76 r
F P
s
P
p
.002 w
.56723 .94661 m .51987 .99077 L .52283 .97207 L p
.914 .98 .76 r
F P
s
P
p
.002 w
.69616 1.18524 m .6817 1.1867 L .71196 1.1933 L p
.226 .745 .837 r
F P
s
P
p
.002 w
.71196 1.1933 m .72986 1.18105 L .69616 1.18524 L p
.226 .745 .837 r
F P
s
P
p
.002 w
.48641 1.06952 m .51694 1.00924 L .51405 1.02748 L p
.947 .716 .556 r
F P
s
P
p
.002 w
.51405 1.02748 m .50393 1.08498 L .48641 1.06952 L p
.947 .716 .556 r
F P
s
P
p
.002 w
.54165 .60247 m .36922 .67805 L .38677 .69565 L p
.769 .778 .861 r
F P
s
P
p
.002 w
.66257 1.18942 m .65129 1.18007 L .6817 1.1867 L p
0 0 0 r
F P
s
P
p
.002 w
.6817 1.1867 m .69616 1.18524 L .66257 1.18942 L p
0 0 0 r
F P
s
P
p
.002 w
.4975 1.43396 m .3729 1.35054 L .39383 1.33809 L p
.378 .544 .893 r
F P
s
P
p
.002 w
.39383 1.33809 m .49974 1.41821 L .4975 1.43396 L p
.378 .544 .893 r
F P
s
P
p
.002 w
.3729 1.35054 m .29959 1.23459 L .3321 1.23055 L p
.561 .577 .828 r
F P
s
P
p
.002 w
.3321 1.23055 m .39383 1.33809 L .3729 1.35054 L p
.561 .577 .828 r
F P
s
P
p
.002 w
.49703 1.14643 m .50393 1.08498 L .52127 1.10028 L p
.783 .479 .474 r
F P
s
P
p
.002 w
.52127 1.10028 m .52818 1.15322 L .49703 1.14643 L p
.783 .479 .474 r
F P
s
P
p
.002 w
.36922 .67805 m .54165 .60247 L .54527 .58152 L p
.76 .777 .867 r
F P
s
P
p
.002 w
.52583 .95313 m .61184 .91819 L .58943 .93247 L p
.882 .965 .844 r
F P
s
P
p
.002 w
.58943 .93247 m .52283 .97207 L .52583 .95313 L p
.882 .965 .844 r
F P
s
P
p
.002 w
.62907 1.19359 m .62074 1.1734 L .65129 1.18007 L p
.106 0 0 r
F P
s
P
p
.002 w
.65129 1.18007 m .66257 1.18942 L .62907 1.19359 L p
.106 0 0 r
F P
s
P
p
.002 w
.54527 .58152 m .35148 .66026 L .36922 .67805 L p
.76 .777 .867 r
F P
s
P
p
.002 w
.46869 1.05389 m .51987 .99077 L .51694 1.00924 L p
.916 .761 .673 r
F P
s
P
p
.002 w
.51694 1.00924 m .48641 1.06952 L .46869 1.05389 L p
.916 .761 .673 r
F P
s
P
p
.002 w
.59567 1.19775 m .59003 1.16671 L .62074 1.1734 L p
.302 0 0 r
F P
s
P
p
.002 w
.62074 1.1734 m .62907 1.19359 L .59567 1.19775 L p
.302 0 0 r
F P
s
P
p
.002 w
.49974 1.41821 m .39383 1.33809 L .41496 1.32552 L p
.326 .492 .87 r
F P
s
P
p
.002 w
.39383 1.33809 m .3321 1.23055 L .3647 1.22649 L p
.548 .547 .806 r
F P
s
P
p
.002 w
.3647 1.22649 m .41496 1.32552 L .39383 1.33809 L p
.548 .547 .806 r
F P
s
P
p
.002 w
.41496 1.32552 m .50202 1.40226 L .49974 1.41821 L p
.326 .492 .87 r
F P
s
P
p
.002 w
.46572 1.13961 m .48641 1.06952 L .50393 1.08498 L p
.788 .557 .586 r
F P
s
P
p
.002 w
.50393 1.08498 m .49703 1.14643 L .46572 1.13961 L p
.788 .557 .586 r
F P
s
P
p
.002 w
.52887 .93395 m .63447 .90378 L .61184 .91819 L p
.848 .942 .883 r
F P
s
P
p
.002 w
.61184 .91819 m .52583 .95313 L .52887 .93395 L p
.848 .942 .883 r
F P
s
P
p
.002 w
.56238 1.20189 m .55918 1.15998 L .59003 1.16671 L p
.458 .015 .107 r
F P
s
P
p
.002 w
.59003 1.16671 m .59567 1.19775 L .56238 1.20189 L p
.458 .015 .107 r
F P
s
P
p
.002 w
.20266 1.24666 m .20968 1.08378 L .24224 1.09088 L p
.667 .666 .84 r
F P
s
P
p
.002 w
.24224 1.09088 m .23487 1.24265 L .20266 1.24666 L p
.667 .666 .84 r
F P
s
P
p
.002 w
.52918 1.20602 m .52818 1.15322 L .55918 1.15998 L p
.556 .205 .328 r
F P
s
P
p
.002 w
.55918 1.15998 m .56238 1.20189 L .52918 1.20602 L p
.556 .205 .328 r
F P
s
P
p
.002 w
.23487 1.24265 m .24224 1.09088 L .27464 1.09794 L p
.668 .655 .828 r
F P
s
P
p
.002 w
.27464 1.09794 m .26719 1.23863 L .23487 1.24265 L p
.668 .655 .828 r
F P
s
P
p
.002 w
.50202 1.40226 m .41496 1.32552 L .4363 1.31283 L p
.249 .414 .831 r
F P
s
P
p
.002 w
.41496 1.32552 m .3647 1.22649 L .3974 1.22242 L p
.53 .507 .775 r
F P
s
P
p
.002 w
.3974 1.22242 m .4363 1.31283 L .41496 1.32552 L p
.53 .507 .775 r
F P
s
P
p
.002 w
.45078 1.03808 m .52283 .97207 L .51987 .99077 L p
.884 .78 .742 r
F P
s
P
p
.002 w
.51987 .99077 m .46869 1.05389 L .45078 1.03808 L p
.884 .78 .742 r
F P
s
P
p
.002 w
.26719 1.23863 m .27464 1.09794 L .30688 1.10497 L p
.668 .642 .814 r
F P
s
P
p
.002 w
.30688 1.10497 m .29959 1.23459 L .26719 1.23863 L p
.668 .642 .814 r
F P
s
P
p
.002 w
.53195 .91452 m .65732 .88923 L .63447 .90378 L p
.821 .921 .903 r
F P
s
P
p
.002 w
.63447 .90378 m .52887 .93395 L .53195 .91452 L p
.821 .921 .903 r
F P
s
P
p
.002 w
.49609 1.21014 m .49703 1.14643 L .52818 1.15322 L p
.611 .34 .482 r
F P
s
P
p
.002 w
.52818 1.15322 m .52918 1.20602 L .49609 1.21014 L p
.611 .34 .482 r
F P
s
P
p
.002 w
.43427 1.13275 m .46869 1.05389 L .48641 1.06952 L p
.784 .607 .661 r
F P
s
P
p
.002 w
.48641 1.06952 m .46572 1.13961 L .43427 1.13275 L p
.784 .607 .661 r
F P
s
P
p
.002 w
.4363 1.31283 m .50432 1.38611 L .50202 1.40226 L p
.249 .414 .831 r
F P
s
P
p
.002 w
.29959 1.23459 m .30688 1.10497 L .33896 1.11197 L p
.669 .625 .797 r
F P
s
P
p
.002 w
.33896 1.11197 m .3321 1.23055 L .29959 1.23459 L p
.669 .625 .797 r
F P
s
P
p
.002 w
.3321 1.23055 m .33896 1.11197 L .37089 1.11893 L p
.668 .605 .775 r
F P
s
P
p
.002 w
.37089 1.11893 m .3647 1.22649 L .3321 1.23055 L p
.668 .605 .775 r
F P
s
P
p
.002 w
.46309 1.21425 m .46572 1.13961 L .49703 1.14643 L p
.64 .433 .587 r
F P
s
P
p
.002 w
.49703 1.14643 m .49609 1.21014 L .46309 1.21425 L p
.64 .433 .587 r
F P
s
P
p
.002 w
.50432 1.38611 m .4363 1.31283 L .45784 1.30002 L p
.129 .289 .759 r
F P
s
P
p
.002 w
.4363 1.31283 m .3974 1.22242 L .4302 1.21834 L p
.504 .452 .732 r
F P
s
P
p
.002 w
.4302 1.21834 m .45784 1.30002 L .4363 1.31283 L p
.504 .452 .732 r
F P
s
P
p
.002 w
.3647 1.22649 m .37089 1.11893 L .40265 1.12585 L p
.666 .579 .747 r
F P
s
P
p
.002 w
.40265 1.12585 m .3974 1.22242 L .3647 1.22649 L p
.666 .579 .747 r
F P
s
P
p
.002 w
.53507 .89483 m .68038 .87453 L .65732 .88923 L p
.799 .903 .914 r
F P
s
P
p
.002 w
.65732 .88923 m .53195 .91452 L .53507 .89483 L p
.799 .903 .914 r
F P
s
P
p
.002 w
.43267 1.0221 m .52583 .95313 L .52283 .97207 L p
.855 .789 .786 r
F P
s
P
p
.002 w
.52283 .97207 m .45078 1.03808 L .43267 1.0221 L p
.855 .789 .786 r
F P
s
P
p
.002 w
.4302 1.21834 m .43427 1.13275 L .46572 1.13961 L p
.655 .498 .659 r
F P
s
P
p
.002 w
.46572 1.13961 m .46309 1.21425 L .4302 1.21834 L p
.655 .498 .659 r
F P
s
P
p
.002 w
.3974 1.22242 m .40265 1.12585 L .43427 1.13275 L p
.663 .544 .71 r
F P
s
P
p
.002 w
.43427 1.13275 m .4302 1.21834 L .3974 1.22242 L p
.663 .544 .71 r
F P
s
P
p
.002 w
.40265 1.12585 m .45078 1.03808 L .46869 1.05389 L p
.775 .641 .713 r
F P
s
P
p
.002 w
.46869 1.05389 m .43427 1.13275 L .40265 1.12585 L p
.775 .641 .713 r
F P
s
P
p
.002 w
.45784 1.30002 m .4302 1.21834 L .46309 1.21425 L p
.465 .375 .669 r
F P
s
P
p
.002 w
.45784 1.30002 m .50666 1.36975 L .50432 1.38611 L p
.129 .289 .759 r
F P
s
P
p
.002 w
.50666 1.36975 m .45784 1.30002 L .47959 1.28708 L p
0 .078 .615 r
F P
s
P
p
.002 w
.46309 1.21425 m .47959 1.28708 L .45784 1.30002 L p
.465 .375 .669 r
F P
s
P
p
.002 w
.47959 1.28708 m .46309 1.21425 L .49609 1.21014 L p
.404 .26 .569 r
F P
s
P
p
.002 w
.53822 .87489 m .70366 .8597 L .68038 .87453 L p
.781 .888 .922 r
F P
s
P
p
.002 w
.68038 .87453 m .53507 .89483 L .53822 .87489 L p
.781 .888 .922 r
F P
s
P
p
.002 w
.45078 1.03808 m .40265 1.12585 L .37089 1.11893 L p
.766 .664 .75 r
F P
s
P
p
.002 w
.49609 1.21014 m .50156 1.27401 L .47959 1.28708 L p
.404 .26 .569 r
F P
s
P
p
.002 w
.50156 1.27401 m .49609 1.21014 L .52918 1.20602 L p
.304 .086 .408 r
F P
s
P
p
.002 w
.37089 1.11893 m .43267 1.0221 L .45078 1.03808 L p
.766 .664 .75 r
F P
s
P
p
.002 w
.41437 1.00595 m .52887 .93395 L .52583 .95313 L p
.831 .793 .815 r
F P
s
P
p
.002 w
.52583 .95313 m .43267 1.0221 L .41437 1.00595 L p
.831 .793 .815 r
F P
s
P
p
.002 w
.52918 1.20602 m .52374 1.26082 L .50156 1.27401 L p
.304 .086 .408 r
F P
s
P
p
.002 w
.52374 1.26082 m .52918 1.20602 L .56238 1.20189 L p
.141 0 .153 r
F P
s
P
p
.002 w
.43267 1.0221 m .37089 1.11893 L .33896 1.11197 L p
.757 .682 .778 r
F P
s
P
p
.002 w
.47959 1.28708 m .50902 1.35318 L .50666 1.36975 L p
0 .078 .615 r
F P
s
P
p
.002 w
.50902 1.35318 m .47959 1.28708 L .50156 1.27401 L p
0 0 .322 r
F P
s
P
p
.002 w
.56238 1.20189 m .54615 1.24749 L .52374 1.26082 L p
.141 0 .153 r
F P
s
P
p
.002 w
.54615 1.24749 m .56238 1.20189 L .59567 1.19775 L p
0 0 0 r
F P
s
P
p
.002 w
.54142 .85468 m .72718 .84472 L .70366 .8597 L p
.767 .876 .926 r
F P
s
P
p
.002 w
.70366 .8597 m .53822 .87489 L .54142 .85468 L p
.767 .876 .926 r
F P
s
P
p
.002 w
.59567 1.19775 m .56878 1.23403 L .54615 1.24749 L p
0 0 0 r
F P
s
P
p
.002 w
.56878 1.23403 m .59567 1.19775 L .62907 1.19359 L p
0 0 0 r
F P
s
P
p
.002 w
.41437 1.00595 m .33896 1.11197 L .30688 1.10497 L p
.749 .695 .8 r
F P
s
P
p
.002 w
.33896 1.11197 m .41437 1.00595 L .43267 1.0221 L p
.757 .682 .778 r
F P
s
P
p
.002 w
.62907 1.19359 m .59163 1.22044 L .56878 1.23403 L p
0 0 0 r
F P
s
P
p
.002 w
.59163 1.22044 m .62907 1.19359 L .66257 1.18942 L p
.462 .878 .753 r
F P
s
P
p
.002 w
.39585 .98961 m .30688 1.10497 L .27464 1.09794 L p
.742 .705 .817 r
F P
s
P
p
.002 w
.30688 1.10497 m .39585 .98961 L .41437 1.00595 L p
.749 .695 .8 r
F P
s
P
p
.002 w
.66257 1.18942 m .61472 1.20671 L .59163 1.22044 L p
.462 .878 .753 r
F P
s
P
p
.002 w
.61472 1.20671 m .66257 1.18942 L .69616 1.18524 L p
.546 .929 .871 r
F P
s
P
p
.002 w
.39585 .98961 m .53195 .91452 L .52887 .93395 L p
.812 .794 .835 r
F P
s
P
p
.002 w
.52887 .93395 m .41437 1.00595 L .39585 .98961 L p
.812 .794 .835 r
F P
s
P
p
.002 w
.50156 1.27401 m .51141 1.33639 L .50902 1.35318 L p
0 0 .322 r
F P
s
P
p
.002 w
.51141 1.33639 m .50156 1.27401 L .52374 1.26082 L p
.722 .681 .136 r
F P
s
P
p
.002 w
.69616 1.18524 m .63804 1.19284 L .61472 1.20671 L p
.546 .929 .871 r
F P
s
P
p
.002 w
.63804 1.19284 m .69616 1.18524 L .72986 1.18105 L p
.589 .94 .929 r
F P
s
P
p
.002 w
.37714 .9731 m .27464 1.09794 L .24224 1.09088 L p
.735 .713 .83 r
F P
s
P
p
.002 w
.27464 1.09794 m .37714 .9731 L .39585 .98961 L p
.742 .705 .817 r
F P
s
P
p
.002 w
.72986 1.18105 m .6616 1.17882 L .63804 1.19284 L p
.589 .94 .929 r
F P
s
P
p
.002 w
.6616 1.17882 m .72986 1.18105 L .76367 1.17684 L p
.612 .936 .958 r
F P
s
P
p
.002 w
.76367 1.17684 m .6854 1.16467 L .6616 1.17882 L p
.612 .936 .958 r
F P
s
P
p
.002 w
.6854 1.16467 m .76367 1.17684 L .79757 1.17262 L p
.625 .926 .972 r
F P
s
P
p
.002 w
.35821 .95639 m .24224 1.09088 L .20968 1.08378 L p
.729 .719 .841 r
F P
s
P
p
.002 w
.24224 1.09088 m .35821 .95639 L .37714 .9731 L p
.735 .713 .83 r
F P
s
P
p
.002 w
.52374 1.26082 m .51384 1.31939 L .51141 1.33639 L p
.722 .681 .136 r
F P
s
P
p
.002 w
.51384 1.31939 m .52374 1.26082 L .54615 1.24749 L p
.889 .921 .53 r
F P
s
P
p
.002 w
.79757 1.17262 m .70944 1.15037 L .6854 1.16467 L p
.625 .926 .972 r
F P
s
P
p
.002 w
.70944 1.15037 m .79757 1.17262 L .83158 1.16839 L p
.632 .915 .98 r
F P
s
P
p
.002 w
.37714 .9731 m .53507 .89483 L .53195 .91452 L p
.796 .794 .851 r
F P
s
P
p
.002 w
.53195 .91452 m .39585 .98961 L .37714 .9731 L p
.796 .794 .851 r
F P
s
P
p
.002 w
.20968 1.08378 m .33907 .9395 L .35821 .95639 L p
.729 .719 .841 r
F P
s
P
p
.002 w
.83158 1.16839 m .73373 1.13592 L .70944 1.15037 L p
.632 .915 .98 r
F P
s
P
p
.002 w
.54615 1.24749 m .5163 1.30216 L .51384 1.31939 L p
.889 .921 .53 r
F P
s
P
p
.002 w
.5163 1.30216 m .54615 1.24749 L .56878 1.23403 L p
.906 .984 .737 r
F P
s
P
p
.002 w
.35821 .95639 m .53822 .87489 L .53507 .89483 L p
.782 .794 .862 r
F P
s
P
p
.002 w
.53507 .89483 m .37714 .9731 L .35821 .95639 L p
.782 .794 .862 r
F P
s
P
p
.002 w
.56878 1.23403 m .51879 1.28471 L .5163 1.30216 L p
.906 .984 .737 r
F P
s
P
p
.002 w
.51879 1.28471 m .56878 1.23403 L .59163 1.22044 L p
.878 .981 .833 r
F P
s
P
p
.002 w
.33907 .9395 m .54142 .85468 L .53822 .87489 L p
.771 .793 .871 r
F P
s
P
p
.002 w
.53822 .87489 m .35821 .95639 L .33907 .9395 L p
.771 .793 .871 r
F P
s
P
p
.002 w
.59163 1.22044 m .52131 1.26702 L .51879 1.28471 L p
.878 .981 .833 r
F P
s
P
p
.002 w
.52131 1.26702 m .59163 1.22044 L .61472 1.20671 L p
.846 .963 .881 r
F P
s
P
p
.002 w
.61472 1.20671 m .52387 1.2491 L .52131 1.26702 L p
.846 .963 .881 r
F P
s
P
p
.002 w
.52387 1.2491 m .61472 1.20671 L .63804 1.19284 L p
.818 .943 .907 r
F P
s
P
p
.002 w
.63804 1.19284 m .52646 1.23093 L .52387 1.2491 L p
.818 .943 .907 r
F P
s
P
p
.002 w
.52646 1.23093 m .63804 1.19284 L .6616 1.17882 L p
.796 .926 .922 r
F P
s
P
p
.002 w
.6616 1.17882 m .52908 1.21252 L .52646 1.23093 L p
.796 .926 .922 r
F P
s
P
p
.002 w
.52908 1.21252 m .6616 1.17882 L .6854 1.16467 L p
.777 .912 .931 r
F P
s
P
p
.002 w
.6854 1.16467 m .53175 1.19386 L .52908 1.21252 L p
.777 .912 .931 r
F P
s
P
p
.002 w
.53175 1.19386 m .6854 1.16467 L .70944 1.15037 L p
.762 .9 .938 r
F P
s
P
p
.002 w
.70944 1.15037 m .53445 1.17494 L .53175 1.19386 L p
.762 .9 .938 r
F P
s
P
p
.002 w
.53445 1.17494 m .70944 1.15037 L .73373 1.13592 L p
.75 .889 .942 r
F P
s
P
p
.002 w
.73373 1.13592 m .53718 1.15575 L .53445 1.17494 L p
.75 .889 .942 r
F P
s
P
p
.002 w
.63035 .03845 m
.89105 .40641 L
s
.89105 .40641 m
.96155 1.42592 L
s
.96155 1.42592 m
.65505 1.10909 L
s
.65505 1.10909 m
.63035 .03845 L
s
.11641 .24018 m
.03845 1.28432 L
s
.03845 1.28432 m
.65505 1.10909 L
s
.65505 1.10909 m
.63035 .03845 L
s
.63035 .03845 m
.11641 .24018 L
s
P
p
p
.002 w
.03845 1.28432 m
.39312 1.56513 L
s
P
P
p
P
% End of Graphics
MathPictureEnd

:[font = output; output; inactive; preserveAspect; endGroup]
Graphics3D["<<>>"]
;[o]
-Graphics3D-
:[font = text; inactive; preserveAspect; endGroup]
Mathematica generates all graphics in PostScript, so that you can resize pictures, and make use of the resolution available on different types of printers.  However, due to space limitations, all of the graphics on this disk have been re-rendered into bitmap form.  Resizing bitmaps may cause considerable distortion of the graphic.
:[font = section; inactive; preserveAspect; startGroup]
Programming in Mathematica
:[font = text; inactive; preserveAspect]

You can use Mathematica not only as a "calculator", but also as a full symbolic programming language.

Many application packages covering specific areas have been or are being written in the Mathematica language.
:[font = subsection; inactive; preserveAspect; startGroup]
 A Graphics Application Package
:[font = input; preserveAspect]

Needs["Graphics`Polyhedra`"]
:[font = text; inactive; preserveAspect]
This loads in a package that defines properties of polyhedra.  The package defines, among other things, the geometry of a dodecahedron.
:[font = input; preserveAspect; startGroup]

Dodecahedron[ ] // Short

:[font = output; output; inactive; preserveAspect; endGroup]
Short["<<>>"]
;[o]
{Polygon[<<1>>], <<10>>, Polygon[{<<5>>}]}
:[font = text; inactive; preserveAspect]
The Dodecahedron function gives the coordinates for the faces of a dodecahedron, shown here in shortened form.
:[font = input; preserveAspect; startGroup]

Show[Graphics3D[ % ]];

:[font = postscript; PostScript; formatAsPostScript; output; inactive; preserveAspect; pictureLeft = 100; pictureWidth = 299.75; pictureHeight = 300.188; endGroup]
%!
%%Creator: Mathematica
%%AspectRatio: 1.00154 
MathPictureStart
%% Graphics3D
/Courier findfont 10  scalefont  setfont
% Scaling calculations
0.00616227 1.03146 0.0257865 1.03146 [
[ 0 0 0 0 ]
[ 1 1.00154 0 0 ]
] MathScale
% Start of Graphics
1 setlinecap
1 setlinejoin
newpath
[ ] 0 setdash
0 g
0 0 m
1 0 L
1 1.00154 L
0 1.00154 L
closepath
clip
newpath
p
.002 w
.094 .26359 m
.02579 .7053 L
s
.02579 .7053 m
.39229 .97575 L
s
.39229 .97575 m
.40721 .57466 L
s
.40721 .57466 m
.094 .26359 L
s
.67582 .02579 m
.91681 .41114 L
s
.91681 .41114 m
.97421 .83531 L
s
.97421 .83531 m
.70397 .4889 L
s
.70397 .4889 m
.67582 .02579 L
s
.094 .26359 m
.02579 .7053 L
s
.02579 .7053 m
.70397 .4889 L
s
.70397 .4889 m
.67582 .02579 L
s
.67582 .02579 m
.094 .26359 L
s
.40721 .57466 m
.91681 .41114 L
s
.91681 .41114 m
.97421 .83531 L
s
.97421 .83531 m
.39229 .97575 L
s
.39229 .97575 m
.40721 .57466 L
s
P
p
.002 w
.33986 .33335 m .44775 .4517 L .6481 .42856 L p
.67 .732 .896 r
F P
s
P
p
.002 w
.44775 .4517 m .33986 .33335 L p
.25563 .67341 L .889 .75 .702 r
F P
s
P
p
.002 w
.25563 .67341 m .40062 .64811 L .44775 .4517 L p
.889 .75 .702 r
F P
s
P
p
.002 w
.44775 .4517 m .40062 .64811 L .5739 .75248 L p
.675 .526 .675 r
F P
s
P
p
.002 w
.6481 .42856 m .44775 .4517 L p
.5739 .75248 L .675 .526 .675 r
F P
s
P
p
.002 w
.40062 .64811 m .25563 .67341 L p
.55705 .85906 L .762 .372 .314 r
F P
s
P
p
.002 w
.55705 .85906 m .5739 .75248 L .40062 .64811 L p
.762 .372 .314 r
F P
s
P
p
.002 w
.48662 .22289 m .33986 .33335 L p
.6481 .42856 L .67 .732 .896 r
F P
s
P
p
.002 w
.6481 .42856 m .68714 .29018 L .48662 .22289 L p
.67 .732 .896 r
F P
s
P
p
.002 w
.5739 .75248 m .73012 .61428 L .6481 .42856 L p
.675 .526 .675 r
F P
s
P
p
.002 w
.6481 .42856 m .73012 .61428 L .83383 .61445 L p
.273 .313 .722 r
F P
s
P
p
.002 w
.68714 .29018 m .6481 .42856 L p
.83383 .61445 L .273 .313 .722 r
F P
s
P
p
.002 w
.5739 .75248 m .55705 .85906 L .72637 .77535 L p
.244 0 .208 r
F P
s
P
p
.002 w
.73012 .61428 m .5739 .75248 L p
.72637 .77535 L .244 0 .208 r
F P
s
P
p
.002 w
.72637 .77535 m .83383 .61445 L .73012 .61428 L p
.244 0 .208 r
F P
s
P
p
.002 w
.33986 .33335 m .21055 .46872 L .25563 .67341 L p
.889 .75 .702 r
F P
s
P
p
.002 w
.33986 .33335 m .48662 .22289 L p
.2704 .45364 L 0 0 0 r
F P
s
P
p
.002 w
.2704 .45364 m .21055 .46872 L .33986 .33335 L p
0 0 0 r
F P
s
P
p
.002 w
.25563 .67341 m .34506 .81048 L .55705 .85906 L p
.762 .372 .314 r
F P
s
P
p
.002 w
.34506 .81048 m .25563 .67341 L .21055 .46872 L p
0 .109 .635 r
F P
s
P
p
.002 w
.83383 .61445 m .80929 .39915 L .68714 .29018 L p
.273 .313 .722 r
F P
s
P
p
.002 w
.68714 .29018 m .80929 .39915 L .67571 .40632 L p
.709 .208 .046 r
F P
s
P
p
.002 w
.48662 .22289 m .68714 .29018 L p
.67571 .40632 L .709 .208 .046 r
F P
s
P
p
.002 w
.72637 .77535 m .55705 .85906 L .34506 .81048 L p
.633 .775 .949 r
F P
s
P
p
.002 w
.21055 .46872 m .2704 .45364 L p
.34506 .81048 L 0 .109 .635 r
F P
s
P
p
.002 w
.80929 .39915 m .83383 .61445 L .72637 .77535 L p
.939 .801 .68 r
F P
s
P
p
.002 w
.67571 .40632 m .45888 .28588 L .48662 .22289 L p
.709 .208 .046 r
F P
s
P
p
.002 w
.48662 .22289 m .45888 .28588 L .2704 .45364 L p
0 0 0 r
F P
s
P
p
.002 w
.2704 .45364 m .36268 .68402 L .34506 .81048 L p
0 .109 .635 r
F P
s
P
p
.002 w
.34506 .81048 m .36268 .68402 L p
.72637 .77535 L .633 .775 .949 r
F P
s
P
p
.002 w
.67571 .40632 m .80929 .39915 L p
.72637 .77535 L .939 .801 .68 r
F P
s
P
p
.002 w
.72637 .77535 m .6183 .65832 L .67571 .40632 L p
.939 .801 .68 r
F P
s
P
p
.002 w
.36268 .68402 m .6183 .65832 L .72637 .77535 L p
.633 .775 .949 r
F P
s
P
p
.002 w
.45888 .28588 m .2704 .45364 L .36268 .68402 L p
.666 .512 .666 r
F P
s
P
p
.002 w
.67571 .40632 m .45888 .28588 L p
.36268 .68402 L .666 .512 .666 r
F P
s
P
p
.002 w
.36268 .68402 m .6183 .65832 L .67571 .40632 L p
.666 .512 .666 r
F P
s
P
p
.002 w
.67582 .02579 m
.91681 .41114 L
s
.91681 .41114 m
.97421 .83531 L
s
.97421 .83531 m
.70397 .4889 L
s
.70397 .4889 m
.67582 .02579 L
s
.094 .26359 m
.02579 .7053 L
s
.02579 .7053 m
.70397 .4889 L
s
.70397 .4889 m
.67582 .02579 L
s
.67582 .02579 m
.094 .26359 L
s
P
p
P
% End of Graphics
MathPictureEnd

:[font = text; inactive; preserveAspect]

This shows the dodecahedron as a three-dimensional graphical object.

As another example, we can use the definitions from the polyhedra package to create an image of a stellated icosahedron, which is often used as an icon for the Mathematica system.
:[font = input; preserveAspect; startGroup]

Show[ Stellate[ Polyhedron[ Icosahedron ]] ];

:[font = postscript; PostScript; formatAsPostScript; output; inactive; preserveAspect; pictureLeft = 100; pictureWidth = 299.75; pictureHeight = 300.188; endGroup; endGroup]
%!
%%Creator: Mathematica
%%AspectRatio: 1.00154 
MathPictureStart
%% Graphics3D
/Courier findfont 10  scalefont  setfont
% Scaling calculations
0.00616227 1.03146 0.0257865 1.03146 [
[ 0 0 0 0 ]
[ 1 1.00154 0 0 ]
] MathScale
% Start of Graphics
1 setlinecap
1 setlinejoin
newpath
[ ] 0 setdash
0 g
0 0 m
1 0 L
1 1.00154 L
0 1.00154 L
closepath
clip
newpath
p
.002 w
.094 .26359 m
.02579 .7053 L
s
.02579 .7053 m
.39229 .97575 L
s
.39229 .97575 m
.40721 .57466 L
s
.40721 .57466 m
.094 .26359 L
s
.67582 .02579 m
.91681 .41114 L
s
.91681 .41114 m
.97421 .83531 L
s
.97421 .83531 m
.70397 .4889 L
s
.70397 .4889 m
.67582 .02579 L
s
.094 .26359 m
.02579 .7053 L
s
.02579 .7053 m
.70397 .4889 L
s
.70397 .4889 m
.67582 .02579 L
s
.67582 .02579 m
.094 .26359 L
s
.40721 .57466 m
.91681 .41114 L
s
.91681 .41114 m
.97421 .83531 L
s
.97421 .83531 m
.39229 .97575 L
s
.39229 .97575 m
.40721 .57466 L
s
P
p
.002 w
.44775 .4517 m .52189 .3873 L .55191 .57098 L closepath p
.37 .395 .757 r
F P
s
P
p
.002 w
.44775 .4517 m .55191 .57098 L .37174 .52072 L closepath p
.786 .442 .405 r
F P
s
P
p
.002 w
.44775 .4517 m .37174 .52072 L .52189 .3873 L closepath p
0 0 0 r
F P
s
P
p
.002 w
.40062 .64811 m .37174 .52072 L .55191 .57098 L closepath p
.702 .725 .868 r
F P
s
P
p
.002 w
.40062 .64811 m .55191 .57098 L .4468 .70453 L closepath p
.366 .042 .277 r
F P
s
P
p
.002 w
.40062 .64811 m .4468 .70453 L .37174 .52072 L closepath p
0 0 .535 r
F P
s
P
p
.002 w
.6481 .42856 m .55191 .57098 L .52189 .3873 L closepath p
.859 .738 .729 r
F P
s
P
p
.002 w
.6481 .42856 m .69485 .48471 L .55191 .57098 L closepath p
.299 .033 .331 r
F P
s
P
p
.002 w
.6481 .42856 m .52189 .3873 L .69485 .48471 L closepath p
.641 .082 0 r
F P
s
P
p
.002 w
.5739 .75248 m .55191 .57098 L .64974 .68452 L closepath p
.311 .298 .683 r
F P
s
P
p
.002 w
.5739 .75248 m .4468 .70453 L .55191 .57098 L closepath p
.873 .707 .674 r
F P
s
P
p
.002 w
.5739 .75248 m .64974 .68452 L .4468 .70453 L closepath p
.6 .813 .982 r
F P
s
P
p
.002 w
.73012 .61428 m .55191 .57098 L .69485 .48471 L closepath p
.643 .687 .876 r
F P
s
P
p
.002 w
.73012 .61428 m .64974 .68452 L .55191 .57098 L closepath p
.721 .348 .342 r
F P
s
P
p
.002 w
.73012 .61428 m .69485 .48471 L .64974 .68452 L closepath p
.971 .845 .659 r
F P
s
P
p
.002 w
.33986 .33335 m .52189 .3873 L .37174 .52072 L closepath p
.698 .565 .701 r
F P
s
P
p
.002 w
.33986 .33335 m .38856 .39238 L .52189 .3873 L closepath p
.608 .076 0 r
F P
s
P
p
.002 w
.33986 .33335 m .37174 .52072 L .38856 .39238 L closepath p
0 0 .52 r
F P
s
P
p
.002 w
.21055 .46872 m .38856 .39238 L .37174 .52072 L closepath p
.712 .79 .912 r
F P
s
P
p
.002 w
.25563 .67341 m .37174 .52072 L .4468 .70453 L closepath p
.706 .538 .659 r
F P
s
P
p
.002 w
.25563 .67341 m .33736 .60212 L .37174 .52072 L closepath p
0 0 0 r
F P
s
P
p
.002 w
.21055 .46872 m .37174 .52072 L .33736 .60212 L closepath p
.817 .41 .277 r
F P
s
P
p
.002 w
.68714 .29018 m .69485 .48471 L .52189 .3873 L closepath p
.654 .533 .704 r
F P
s
P
p
.002 w
.68714 .29018 m .52189 .3873 L .6024 .36674 L closepath p
0 0 0 r
F P
s
P
p
.002 w
.48662 .22289 m .52189 .3873 L .38856 .39238 L closepath p
.899 .805 .747 r
F P
s
P
p
.002 w
.48662 .22289 m .6024 .36674 L .52189 .3873 L closepath p
.217 .339 .774 r
F P
s
P
p
.002 w
.25563 .67341 m .4468 .70453 L .33736 .60212 L closepath p
.559 .729 .954 r
F P
s
P
p
.002 w
.55705 .85906 m .4468 .70453 L .64974 .68452 L closepath p
.666 .487 .633 r
F P
s
P
p
.002 w
.55705 .85906 m .52189 .7131 L .4468 .70453 L closepath p
.079 .258 .746 r
F P
s
P
p
.002 w
.34506 .81048 m .4468 .70453 L .52189 .7131 L closepath p
.145 0 0 r
F P
s
P
p
.002 w
.34506 .81048 m .33736 .60212 L .4468 .70453 L closepath p
.927 .746 .637 r
F P
s
P
p
.002 w
.68714 .29018 m .6024 .36674 L .69485 .48471 L closepath p
.952 .764 .613 r
F P
s
P
p
.002 w
.80929 .39915 m .69485 .48471 L .6024 .36674 L closepath p
.593 .717 .93 r
F P
s
P
p
.002 w
.83383 .61445 m .64974 .68452 L .69485 .48471 L closepath p
.633 .483 .662 r
F P
s
P
p
.002 w
.83383 .61445 m .69485 .48471 L .68945 .56473 L closepath p
.804 .349 .159 r
F P
s
P
p
.002 w
.80929 .39915 m .68945 .56473 L .69485 .48471 L closepath p
0 0 .084 r
F P
s
P
p
.002 w
.83383 .61445 m .68945 .56473 L .64974 .68452 L closepath p
.702 .816 .936 r
F P
s
P
p
.002 w
.55705 .85906 m .64974 .68452 L .52189 .7131 L closepath p
.92 .834 .744 r
F P
s
P
p
.002 w
.72637 .77535 m .64974 .68452 L .68945 .56473 L closepath p
.065 .115 .604 r
F P
s
P
p
.002 w
.72637 .77535 m .52189 .7131 L .64974 .68452 L closepath p
.67 .196 .118 r
F P
s
P
p
.002 w
.21055 .46872 m .33736 .60212 L .38856 .39238 L closepath p
.624 .472 .656 r
F P
s
P
p
.002 w
.48662 .22289 m .38856 .39238 L .6024 .36674 L closepath p
.661 .476 .624 r
F P
s
P
p
.002 w
.45888 .28588 m .6024 .36674 L .38856 .39238 L closepath p
.635 .791 .957 r
F P
s
P
p
.002 w
.45888 .28588 m .38856 .39238 L .4861 .50638 L closepath p
.22 .231 .659 r
F P
s
P
p
.002 w
.2704 .45364 m .38856 .39238 L .33736 .60212 L closepath p
.947 .817 .683 r
F P
s
P
p
.002 w
.2704 .45364 m .4861 .50638 L .38856 .39238 L closepath p
.707 .293 .259 r
F P
s
P
p
.002 w
.2704 .45364 m .33736 .60212 L .4861 .50638 L closepath p
.628 .699 .896 r
F P
s
P
p
.002 w
.34506 .81048 m .52189 .7131 L .33736 .60212 L closepath p
.648 .528 .703 r
F P
s
P
p
.002 w
.36268 .68402 m .33736 .60212 L .52189 .7131 L closepath p
.722 .218 .033 r
F P
s
P
p
.002 w
.36268 .68402 m .4861 .50638 L .33736 .60212 L closepath p
.184 0 .222 r
F P
s
P
p
.002 w
.80929 .39915 m .6024 .36674 L .68945 .56473 L closepath p
.705 .534 .654 r
F P
s
P
p
.002 w
.45888 .28588 m .4861 .50638 L .6024 .36674 L closepath p
.892 .722 .664 r
F P
s
P
p
.002 w
.67571 .40632 m .68945 .56473 L .6024 .36674 L closepath p
0 .115 .646 r
F P
s
P
p
.002 w
.67571 .40632 m .6024 .36674 L .4861 .50638 L closepath p
.266 0 .159 r
F P
s
P
p
.002 w
.72637 .77535 m .68945 .56473 L .52189 .7131 L closepath p
.697 .564 .701 r
F P
s
P
p
.002 w
.6183 .65832 m .52189 .7131 L .68945 .56473 L closepath p
0 0 0 r
F P
s
P
p
.002 w
.6183 .65832 m .4861 .50638 L .52189 .7131 L closepath p
.296 .351 .75 r
F P
s
P
p
.002 w
.36268 .68402 m .52189 .7131 L .4861 .50638 L closepath p
.876 .757 .728 r
F P
s
P
p
.002 w
.67571 .40632 m .4861 .50638 L .68945 .56473 L closepath p
.697 .742 .886 r
F P
s
P
p
.002 w
.6183 .65832 m .68945 .56473 L .4861 .50638 L closepath p
.786 .409 .341 r
F P
s
P
p
.002 w
.67582 .02579 m
.91681 .41114 L
s
.91681 .41114 m
.97421 .83531 L
s
.97421 .83531 m
.70397 .4889 L
s
.70397 .4889 m
.67582 .02579 L
s
.094 .26359 m
.02579 .7053 L
s
.02579 .7053 m
.70397 .4889 L
s
.70397 .4889 m
.67582 .02579 L
s
.67582 .02579 m
.094 .26359 L
s
P
p
P
% End of Graphics
MathPictureEnd

:[font = subsection; inactive; preserveAspect; startGroup]
  More on Programming
:[font = text; inactive; preserveAspect]
There are several styles of programming in Mathematica.  One of them is procedural programming, as you would find in a standard structured programming language such as C or Pascal.

Another is "rule-based programming".  The idea is to give transformation rules which specify how Mathematica should transform expressions it receives as input. 

You can give rules that mimic the formulas you might find in a mathematics textbook.

Here is an example of how you might teach Mathematica about a new form of logarithm function, called nlog.
:[font = input; preserveAspect; startGroup]
nlog[a b c d^2]

:[font = output; output; inactive; preserveAspect; endGroup]
nlog[a*b*c*d^2]















































;[o]
            2
nlog[a b c d ]
:[font = text; inactive; preserveAspect]

Mathematica  initially knows nothing about our new function, so it does nothing to expressions involving nlogs.
:[font = input; preserveAspect]

nlog[x_ y_] := nlog[x] + nlog[y]

:[font = text; inactive; preserveAspect]
This tells Mathematica how to expand logarithms of products.
:[font = input; preserveAspect; startGroup]

nlog[a b c d^2]

:[font = output; output; inactive; preserveAspect; endGroup]
nlog[a] + nlog[b] + nlog[c] + nlog[d^2]















































;[o]
                                    2
nlog[a] + nlog[b] + nlog[c] + nlog[d ]
:[font = text; inactive; preserveAspect]
Now Mathematica can expand the nlog out. 
:[font = input; preserveAspect]

nlog[x_^n_] := n nlog[x]

:[font = text; inactive; preserveAspect]
This gives a rule for nlog of a power.
:[font = input; preserveAspect; startGroup]
nlog[a b c d^2]

:[font = output; output; inactive; preserveAspect; endGroup]
nlog[a] + nlog[b] + nlog[c] + 2*nlog[d]















































;[o]
nlog[a] + nlog[b] + nlog[c] + 2 nlog[d]
:[font = text; inactive; preserveAspect]
Now Mathematica can expand the expression completely.
:[font = text; inactive; preserveAspect]

Another style of programming you can use in Mathematica is functional programming.  In functional programming, you specify a collection of functions to apply.  This style of programming often yields compact, elegant programs that make good use of Mathematica's many integrated capabilities.
:[font = text; inactive; preserveAspect]
This is a program that plots the solutions to a polynomial equation as points in the complex plane.
:[font = input; preserveAspect]
RootPlot[poly_, z] :=
	ListPlot[{Re[z], Im[z]} /. NSolve[ poly == 0, z],
                        AspectRatio -> Automatic ]                          
:[font = text; inactive; preserveAspect]
You can immediately use the program to plot solutions.
:[font = input; preserveAspect; startGroup]

RootPlot[ z^7 - 1 , z];

:[font = postscript; PostScript; formatAsPostScript; output; inactive; preserveAspect; pictureLeft = 100; pictureWidth = 292.188; pictureHeight = 299.688; endGroup]
%!
%%Creator: Mathematica
%%AspectRatio: 1.02572 
MathPictureStart
%% Graphics
/Courier findfont 10  scalefont  setfont
% Scaling calculations
0.475193 0.500998 0.512858 0.500998 [
[(-0.75)] .09944 .51286 0 2 Msboxa
[(-0.5)] .22469 .51286 0 2 Msboxa
[(-0.25)] .34994 .51286 0 2 Msboxa
[(0.25)] .60044 .51286 0 2 Msboxa
[(0.5)] .72569 .51286 0 2 Msboxa
[(0.75)] .85094 .51286 0 2 Msboxa
[(1)] .97619 .51286 0 2 Msboxa
[(-1)] .46269 .01186 1 0 Msboxa
[(-0.5)] .46269 .26236 1 0 Msboxa
[(0.5)] .46269 .76336 1 0 Msboxa
[(1)] .46269 1.01386 1 0 Msboxa
[ -0.001 -0.001 0 0 ]
[ 1.001 1.02672 0 0 ]
] MathScale
% Start of Graphics
1 setlinecap
1 setlinejoin
newpath
[ ] 0 setdash
0 g
p
p
.002 w
.09944 .51286 m
.09944 .51911 L
s
P
[(-0.75)] .09944 .51286 0 2 Mshowa
p
.002 w
.22469 .51286 m
.22469 .51911 L
s
P
[(-0.5)] .22469 .51286 0 2 Mshowa
p
.002 w
.34994 .51286 m
.34994 .51911 L
s
P
[(-0.25)] .34994 .51286 0 2 Mshowa
p
.002 w
.60044 .51286 m
.60044 .51911 L
s
P
[(0.25)] .60044 .51286 0 2 Mshowa
p
.002 w
.72569 .51286 m
.72569 .51911 L
s
P
[(0.5)] .72569 .51286 0 2 Mshowa
p
.002 w
.85094 .51286 m
.85094 .51911 L
s
P
[(0.75)] .85094 .51286 0 2 Mshowa
p
.002 w
.97619 .51286 m
.97619 .51911 L
s
P
[(1)] .97619 .51286 0 2 Mshowa
p
.001 w
.12449 .51286 m
.12449 .51661 L
s
P
p
.001 w
.14954 .51286 m
.14954 .51661 L
s
P
p
.001 w
.17459 .51286 m
.17459 .51661 L
s
P
p
.001 w
.19964 .51286 m
.19964 .51661 L
s
P
p
.001 w
.24974 .51286 m
.24974 .51661 L
s
P
p
.001 w
.27479 .51286 m
.27479 .51661 L
s
P
p
.001 w
.29984 .51286 m
.29984 .51661 L
s
P
p
.001 w
.32489 .51286 m
.32489 .51661 L
s
P
p
.001 w
.37499 .51286 m
.37499 .51661 L
s
P
p
.001 w
.40004 .51286 m
.40004 .51661 L
s
P
p
.001 w
.42509 .51286 m
.42509 .51661 L
s
P
p
.001 w
.45014 .51286 m
.45014 .51661 L
s
P
p
.001 w
.50024 .51286 m
.50024 .51661 L
s
P
p
.001 w
.52529 .51286 m
.52529 .51661 L
s
P
p
.001 w
.55034 .51286 m
.55034 .51661 L
s
P
p
.001 w
.57539 .51286 m
.57539 .51661 L
s
P
p
.001 w
.62549 .51286 m
.62549 .51661 L
s
P
p
.001 w
.65054 .51286 m
.65054 .51661 L
s
P
p
.001 w
.67559 .51286 m
.67559 .51661 L
s
P
p
.001 w
.70064 .51286 m
.70064 .51661 L
s
P
p
.001 w
.75074 .51286 m
.75074 .51661 L
s
P
p
.001 w
.77579 .51286 m
.77579 .51661 L
s
P
p
.001 w
.80084 .51286 m
.80084 .51661 L
s
P
p
.001 w
.82589 .51286 m
.82589 .51661 L
s
P
p
.001 w
.87599 .51286 m
.87599 .51661 L
s
P
p
.001 w
.90104 .51286 m
.90104 .51661 L
s
P
p
.001 w
.92609 .51286 m
.92609 .51661 L
s
P
p
.001 w
.95114 .51286 m
.95114 .51661 L
s
P
p
.001 w
.07439 .51286 m
.07439 .51661 L
s
P
p
.001 w
.04934 .51286 m
.04934 .51661 L
s
P
p
.001 w
.02429 .51286 m
.02429 .51661 L
s
P
p
.002 w
0 .51286 m
1 .51286 L
s
P
p
.002 w
.47519 .01186 m
.48144 .01186 L
s
P
[(-1)] .46269 .01186 1 0 Mshowa
p
.002 w
.47519 .26236 m
.48144 .26236 L
s
P
[(-0.5)] .46269 .26236 1 0 Mshowa
p
.002 w
.47519 .76336 m
.48144 .76336 L
s
P
[(0.5)] .46269 .76336 1 0 Mshowa
p
.002 w
.47519 1.01386 m
.48144 1.01386 L
s
P
[(1)] .46269 1.01386 1 0 Mshowa
p
.001 w
.47519 .06196 m
.47894 .06196 L
s
P
p
.001 w
.47519 .11206 m
.47894 .11206 L
s
P
p
.001 w
.47519 .16216 m
.47894 .16216 L
s
P
p
.001 w
.47519 .21226 m
.47894 .21226 L
s
P
p
.001 w
.47519 .31246 m
.47894 .31246 L
s
P
p
.001 w
.47519 .36256 m
.47894 .36256 L
s
P
p
.001 w
.47519 .41266 m
.47894 .41266 L
s
P
p
.001 w
.47519 .46276 m
.47894 .46276 L
s
P
p
.001 w
.47519 .56296 m
.47894 .56296 L
s
P
p
.001 w
.47519 .61306 m
.47894 .61306 L
s
P
p
.001 w
.47519 .66316 m
.47894 .66316 L
s
P
p
.001 w
.47519 .71326 m
.47894 .71326 L
s
P
p
.001 w
.47519 .81346 m
.47894 .81346 L
s
P
p
.001 w
.47519 .86356 m
.47894 .86356 L
s
P
p
.001 w
.47519 .91366 m
.47894 .91366 L
s
P
p
.001 w
.47519 .96376 m
.47894 .96376 L
s
P
p
.002 w
.47519 0 m
.47519 1.02572 L
s
P
P
0 0 m
1 0 L
1 1.02572 L
0 1.02572 L
closepath
clip
newpath
p
.008 w
.97619 .51286 Mdot
.02381 .29548 Mdot
.78756 .90455 Mdot
.36371 .02442 Mdot
.36371 1.0013 Mdot
.78756 .12116 Mdot
.02381 .73023 Mdot
P
% End of Graphics
MathPictureEnd

:[font = text; inactive; preserveAspect]
Here is a program written in functional programming style, which finds the first n terms in the continued fraction decomposition of the number x.
:[font = input; preserveAspect]
ContinuedFraction[x_Real, n_Integer] :=
 Floor[ NestList[ 
     Function[{u}, 1/(u - Floor[u])], x, n - 1] ] 
:[font = input; preserveAspect; startGroup]
ContinuedFraction[.34252515, 7]

:[font = output; output; inactive; preserveAspect; endGroup; endGroup; endGroup]
{0, 2, 1, 11, 2, 2, 1}















































;[o]
{0, 2, 1, 11, 2, 2, 1}
:[font = section; inactive; preserveAspect; startGroup]
The Mathematica User Interface
:[font = text; inactive; preserveAspect]
Mathematica consists of two parts -- the "kernel", which actually does computations, and the "front end", which deals with interaction with the user.  The kernel of Mathematica is essentially the same on all computers that support Mathematica.  The front end, on the other hand, works differently on different kinds of computers. 
:[font = text; inactive; preserveAspect; endGroup; endGroup]
This file is an example of a notebook.  Notebooks consist of a mixture of text, graphics, and Mathematica input.

Notebooks can be used like "interactive textbooks" --  you read the text in the notebook, then use the Mathematica input in the notebook to perform calculations.
^*)