// to compile do from folder above - C:\projects\Cypher>javac -cp ../ Cypher100.java
// to cypher from same folder  -   C:\projects\Cypher>java  -cp ../ Cypher.Cypher100 C example2.txt 1234-abcd-5678-efgh>x.x
// to decypher from same folder  - C:\projects\Cypher>java -cp ../ Cypher.Cypher100  D x.x 1234-abcd-5678-efgh

//===============================================================================================================
//	Cypher100.java		Authour David Ratcliffe		Version: v1.0
//
//	This program is designed to convert a plain text message from a text file to cypherable text or vice versa.
//	The cypher array is arrayed 100 times with the key can be alpha/nmeric in this programs,
//	to execute do as follows :	java -cp ../ Cypher.Cypher100 C example2.txt 1234-abcd-5678-efgh>x.x
//					java -cp ../ Cypher.Cypher100  D x.x 1234-abcd-5678-efgh
//
//	parameter 1 - can be C or D; if C then converts normal text to cyphred text, if D converts cyphered text to normal text
//	parameter 2 - filename of file containing text to be translated, can include path if required
//	parameter 3 - alpha/numeric string can be any length, the longer the slower

package Cypher;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;

public class Cypher100
{
				//	 012345678901234567890123456789012345678901234567890123456789012345678901234567890123
	private String keyStr = 	"FX-;4$zusTr!.x2ytV?{£)g_HnWBNC]Z,R hLafQI6wS[PG=YoDlkAc*emq7+509KiOjdEv%(UM:8}p1Jb#3";
	private String baseStr = 	"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:;?[]-+=_{}#£$%!()*";
	private String[] cypherArray = {
					"mF8{dO tG£kB1[oV}uH(iAp0;jR!fU]aT$sQ%gy6eN7#lW9?bP4+K=rw5*nx2I:M.Y-Zchq3,LSX_zC)JDvE",
					"G£kB1[oV}uR!fU]aTbP4+K=rw5*nxchq3,LSXp_zC)JDvEmF8{dO tQ%gy6eN7H(iA0;j$s#lW9?2I:M.Y-Z",
					"oV}]aTbP4+nLSX_zCF8{dO tQ%gy6e;j$s#lW9?2I:M.Y-ZG£kB1[xchqp3,)JDvEmN7H(iA0K=rw5*uR!fU",
					"P4+nLSX_zCF8{dO tQj$s#lW9?2I:B1[xcDvEmN7H(iA0K=rw5*uR!fUoV}]aTb%gy6e;pM.Y-ZG£khq3,)J",
					"SXj$s:B1[xcDvEiA0K=rw5*uR!fUoV}]aTb%gy6e;M.Yq3,)JP4+nL{dO tQmNp7H(#lW9?2I-ZG£kh_zCF8",
					"B1vEiA0K=rw5*uR!fUoV}]aTb%gy6e;M)JdO tQmN7H(#lW9?2I-ZG£kh_zCF8SXj$s:.Yq3,P4+nL{p[xcD",
					"0K*uR!fUoV}gy6e;M)JdO tQmN7H(#lW9?2I_zCF8SXj$s:.Yq3,P4+nL{[xcDB1vEiA]aTb%-ZG£kh=rwp5",
					"R}pgy6e;M)JdO tQ(#lW9?2I_zCF8SXj$s:.+nL{[xcDB1vEiA]aTb%-ZG£kh=rw50K*uYq3,P4mN7H!fUoV",
					"M tQ(#lW9?F8SXj$s:.+nL{pB1vEiA-ZG£kh=rw50K*uYq3,P4mN7H!fUoVR}gy6e;]aTb%2I_zC)JdO[xcD",
					"#F8SXj$s:.+nLA-ZG£kh=rw50K,P4mN7HR}gy6pe;]aTb%2I_zC)JdO[xcDM tQ(*uYq3!fUoV{B1vEilW9?",
					",P4mj$s:.#F8SXilW9?[xcDM ;]aTb%3!fU7HR}+nLA-qoV{BzC)Jde2I_OKNgy6=rw50ptkhQZG£(*uY1vE",
					"$s:.#F8SXilW9?[xcaTb%HR}qoV{BzC)Jde2I_OKNgy6=rw50ptkhQZG£(*uY1vE,P4mj+nLA-DM ;]3!fU7",
					"8SXilW9?[xcaTb%V{BzC)Jde2I_OKNgy6=rw50ptk£(*uYP4mj+nLA-DM ;]3!fU7$s:.#FhQZG1vE,HR}qo",
					"W9?[xcaTb%V)Jde2I6=rw50ptk£(*uYP4mj+nLA-DM 7$s:.#FhQZG1vE,HR}qo8SXil;]3!fU_OKNgy{BzC",
					"Tb%V)Jde2w50ptk£(*uYP4LA-DM 7$s:.#FhQ,HR}qo8SXil;]3!fU_OKNgy{BzCW9?[xcaZG1vEI6=rmj+n",
					"Jde2w50ptk£4LA-DM 7$s:.#FhQ,HR}qo8SXil;]3!fU_OKNgCW9?[xcaZI6=rmj+nTb%V)y{BzG1vE(*uYP",
					"50ptkM 7$s:.#F}qo8SXil;]3!fgCW9?[xcaZI6=rmj+nTb%V)y{BzG1vE(*uYPJde2wU_OKNhQ,HR£4LA-D",
					"M 7$s:.#F}qo8SXilfgCW9?[xcaZI6=rmj+nTbzG1vE(*uYPJde2wU_OKNhQ,HR£4LA-D50ptk%V)y{B;]3!",
					"F}qo8SXilf[xcaZI6=rmj+nTbzG1vE(*uYPJde2wU_OKHR£4LA-D50ptk%V)y{B;]3!M 7$s:.#NhQ,gCW9?",
					"Xilf[x=rmj+nTbz(*uYPJde2wU_OKHR£4LA-D50ptk%V]3!M 7$s:.#NhQ,gCW9?F}qo8S)y{B;G1vEcaZI6",
					"x=rmj+*uYPJde2wU_OKHR£40ptk%V]3!M 7$s:.gCW9?F}qo8S)y{B;G1vEcaZI6Xilf[#NhQ,LA-D5nTbz(",
					"*uYPJde2wU£40ptk%V]3!M 7$s:.gCW9?F})y{B;G1vEcaZI6Xilf[#NhQ,LA-D5nTbz(x=rmj+qo8S_OKHR",
					"de2wU£40p]3!M 7$s:.gCW9?F})y{B;G1vEcaZI6XilfQ,LA-D5nTbz(x=rmj+qo8S_OKHR*uYPJ[#Nhtk%V",
					"£40p]3:.gCW9?F})y{B;caZI6XilfQ,LA-D5nTbz(x=rm8S_OKHR*uYPJ[#Nhtk%Vde2wUj+qoG1vE!M 7$s",
					":.gCW9?F})yaZI6XilfQ,LA-D5(x=rm8S_OKHR*uYPJ[#Nhtk%VdeqoG1vE!M 7$s£40p]32wUj+nTbz{B;c",
					"?F})6XilfQ,x=rm8S_OKHR*uYPJ[#Nhtk%VdeqoG1vE!M 0p]32wUj+nTbz{B;c:.gCW97$s£4LA-D5(yaZI",
					"ilfQ,x=rm8S_OKHR*uYPJ[k%VdeqoG1vE!M 0p]32wUj+nTbz{B;c:.g$s£4LA-D5(yaZI?F})6XCW97#Nht",
					",x=rm8SHR*uYPJ[k%VdeqoG1vEp]32wUj+nTbz{B;c:.g$s£4LA-aZI?F})6XCW97#NhtilfQD5(y!M 0_OK",
					"m8SHR*uVdeqoG1vEp]32wUj+nTbz.g$s£4LA-aZI?F})6XCW97#Nh5(y!M 0_OK,x=rtilfQD{B;c:YPJ[k%",
					"deqoG1vEp]32wUj+n.g$s£4LA-aZI?F})6XCW97#Nh5(y!M 0_OK,x=lfQD{B;c:YPJ[k%m8SHR*uVrtTbzi",
					"G1vEp]32n.g$s£4LA-aZI?F})6XCW97#Nh5(y!K,x=lfQD{B;c:YPJ[k%m8SHrtTbzideqoR*uVM 0_OwUj+",
					"Ep]32s£4LA-aZI?F})6XCh5(y!K,x=lfQD{B;c:YPJ[k%m8SHrteqoR*uVM 0_OwUj+G1vTbzidW97#Nn.g$",
					"£4LA-aZI?FCh5(y!K,x=lfQD{B;c:Ym8SHrteqoR*uVM 0_Ow1vTbzidW97#Nn.g$Ep]32sUj+GPJ[k%})6X",
					"I?FCh5,x=lfQD{B;c:YmeqoR*uVM 0_Ow1vTbzidW97#Nn.g2sUj+GPJ[k%})6X£4LA-aZ$Ep]38SHrt(y!K",
					"x=lfQD{B;c:YmeqoR 0_Ow1vTbzidW97#Nn.g2sUj+GPJ[k%4LA-aZ$Ep]38SHrt(y!KI?FCh5,})6X£*uVM",
					"{B;c:YmeqoR 0bzidW97#Nn.g2sUj+GPJ[k%4LA-aZ$Ep]38SHrKI?FCh5,})6X£*uVMx=lfQDt(y_Ow1vT!",
					"YmeqoR W97#Nn.g2sUj+4LA-aZ$Ep]38SHrKI?FCh5,}£*uVMx=lfQDt(y_Ow1vT!{B;c:)6XGPJ[k%0bzid",
					" W97#Nn.g2sUj-aZ$Ep]38SHrKI?FCh5,}x=lfQDt(y_Ow1vT!{B;GPJ[k%0bzidYmeqoRc:)6X£*uVM+4LA",
					".g2sUj-aZ$Ep]38SHrKI?FCh5,}x=lfQDt(y_Ow1vT!{B;GPJ[k%0bzidYmc:)6X£*uVM+4LA W97#NneqoR",
					"j-aZ$Ep]38SHrKI?FCh5,}x=(y_Ow1vT!{B;GPJ[k%0bzidYmc:)6X£4LA W97#NneqoR.g2sU*uVM+lfQDt",
					"]38SHrKI?FCh5,}x=(yT!{B;GPJ[k%0bzidYmc:)6X£4LA W97#NR.g2sU*uVM+lfQDtj-aZ$Epneqo_Ow1v",
					"rKI?FCh5,}x=(yT!%0bzidYmc:)6X£4LA W97#NR.g2sU*fQDtj-aZ$Epneqo_Ow1v]38SHuVM+l{B;GPJ[k",
					"h5,}x=(yT!%0bzidYm£4LA W97#NR.g2sU*fQDtj-aZ$Epneqo_Ow1HuVM+l{B;GPJ[krKI?FCv]38Sc:)6X",
					"h5,}x=(yT!%0bzidYm£4LA W97#NR.g2sU*fQDtj-aZ$Epneqo_Ow1HuVM+l{B;GPJ[krKI?FCv]38Sc:)6X",
					"?MagG.WjIL)u[3Ul+=p s0emboORcy(#X:%_!rx{£z2BZShAiCTvFfnVQ7}$wd*-6;,KYtE9DkH15qN4P8J]",
					"jXx2]p*_QMmwe £ifuZv!{;3.0Y#$T=NEG1o(aLn%l6?Ig:dJA7R)FW9CS4k5[rz8V}tBU,DKHhOq-ybsP+c",
					"ArI}:_.n(B1K5V{24czXOPjH*EGw;#%i=shMCaf[T?NJRFl6d)L7bQYDS Zk!u]Wq+xeg-0U$pt3,8o9vmy£",
					"jwORvfUCFa*(06u%PB_VN$zcd]9q)IXkmD#.;r?hG,!M=}4ZE2HosyJ+TKbe[ x71Q8g:pYt-i3l5AS{n£LW",
					"df#7KM;kC(ER5:lP+,zX*]u%3a0Hnt)9pmL8ioqWZchNxg£ V.e!G_UQ-$vw?D[6FyYI=4{STr1bO}2JBAsj",
					"!GuMSja?8b(TBV6]0#{A=d*N)+qD7IF142LzoK_rm9}fgex.w3p,tlZc-nPEJRkHXC%£v;QsOiYU$:W[ y5h",
					"3w%1a?F5Rj9i:p eU{8_ZN+P6IQcAdfXgCHr(D0S=lsEkB!GTJ4$£KyV-Ox,tqM;W)]v#Y*z.o2nmLh[}7bu",
					"mU.rf$kjec!)x%:MbazJW;8Z]BYK L-F#XSAv}(TGNCyu5,27{QE*diP=w?V_3gs£+9lIt[H0oh6R1DpOnq4",
					", tS1*N{7E!5%du2L4Yhej]Da#Hn8?sR}U$W6£[xXGOlMv)=(m0IApyJ9qFK-+PVzgfr:wZiBo;Q3bkc_CT.",
					"=)GlU1opdf4,P{£#njq9K3(2wvN;7HbuyE%6cR$FS:k.sIgr!J8}Z+X*VTA[e Ohi5?aLQM-Y0BxCWz_Dmt]",
					"]6}gd!;KeYNM2(BZpy%QDvU[5Xm*FiGTr7#+9 jl8wbRHcJk,nE_h)CzO£u=:Pox.0tqV$4as{L?W-1SIf3A",
					"+:Y].HD#M-XTu6o5Kx$)JO048nFINLj{bh;Qf2t!PB %apC?wvRE=yA,31Sk9Z(lUr7*gsW[£c_qmdeGzi}V",
					")p$Kb nel8DiL4?V,hG{AXmYdsR9IFBaOz]5k}!%;.f*r:wCvPSZgM0#qWHtcjQE+7x1£oN=36U_(J2T[yu-",
					"_jcm[$S5v*J!B#X l8gVWLQq-O2{PH0t+9)Gd41aF3f}%(u.NyiTA:Y£esM7x?6k=;EnIZ]rhKC,bwRpUoDz",
					"-1rS2=g)h;fb0GjqAo5VO*3_de.,8zlMY#[XQ4ac:£7!m IETk?LUCpW{t$yvJ(P}nBu6+x]Ri9%wNDZFsHK",
					"(-2P7MN[$r9nwx8QLk]h3,C;qpbvd:{£TZEfJ#%*KI)!5BX_zRc+a}GtW1j6AyeO=o SU0iH.4m?uDVsYFgl",
					"leqK=1{DYgrTR6cLdim39-?U8CvW£20[s%uSZXwH#$.NztxI*hAp,+O}7:ME5]bGf4j_JB(aFkoy)PQ !;nV",
					"w4y *MbGh]8mZEIOY£+HWiv0Kn9!,;k%tgQxA1{cloV_j}X7TU.(65$u-=#3)rSDe:Nq?CPBpRad[zJL2Fsf",
					"4FhjqO#[*?EXM,ZT5J6n)$vUICrctR]W_z3£D.pbxS0Vyd2Ysi-}kfH:Nul;G+819gA Q%!a(wPmBe{LK=o7",
					"l()eMF9SdA{0:h5]+-UZgWRs7JQtEo6Dkzc?G!%2x=L p$[4XnfV1q3r8.y#NvYjwIabTKHu£mC}iO*;P_B,",
					"?NFl*udW]XH82mc$n£IJf4Et=Ma-i.+(;S_Y{OehyV)x}oZpq3s,Uz[jDKB16v Cgw:RAL!rbT0Q9G57#kP%",
					")zNE=IJFilVQUDsb,1 y[9XR}Aoq!_u8£$]O2gP*%vadm:#C6t.Zw{xcMBL+k3T?fS(n-5Khrj;ep47GHY0W",
					"S.AQBfXrEuo!hZ3O:x£V1DT0dU,Nb}g7L8;eMwiG[c)*+5R%pa 4tnI69mq_=z-$#jlkK{CH2Ysv]?FP(WyJ",
					"}I£udZ=UsTB_8v]hirakp9t7b651NH+%M4E?JXm;OjVeow:CDg$!W3-Y#n AxRl{*Q.0PKG)q,2zF[SyfcL(",
					"%kI7NF!S12t]e*rn[,6)g?O5 b0LzQmK{D+Mf8joC3$£GXuEa.lcv;qyYJAw-d(:Vx4R_is9p#BZWUh}=PTH",
					"a7dW-+!RU)O}F,=.[YlLSz3os ?xGqj%NkJ{B]*TnbviV:A0D(948Pf_$QZXupw5IEMrmgKye#hHc12tC6;£",
					"Y£?wJ_iyL!baTo(.}m[4IxnS)F],5dA-UO8l 1cMzXjhfNH0k*:R$DQK=Z{#CEVu7p93+%2B6qvgstG;WrPe",
					"oe_uNm0#6(g+D;[H1*j3q)PhtQ!vYZL-]Ws{2VA7.kd94fXFzpiTR,y BlUM£w5$S%r:?n=JbEcIOx}aKC8G",
					"(kg=Cit+£%y;lR !dMq.U6cTvN#*Z5bnHKV8OwLmEsB43$][)SoxI,A?:X01he_r-p{P2DfQaz}uF7WJ9YGj",
					"Ig3u*EhQkbUd0ZaT,SvXBA#G£PVx7{W=9Rrqm_[L Fnpi6!])(Jyot8w?DzCM;:O4HleNY}$c5%-+.1j2Kfs",
					"k= :tyzcd!-fv$(AiaM58)}bXKCQeT2HmDB#u0gxY4lNZE9.p,IUOosFS;7Vj6£{L]WJq[h3%Pn1RwG*_+?r",
					"FGzoa$McVjQkuJR=0H-KLpTE4£NUygZ;{8fB}x7,Ods[WYI5 PXA!?3n.%tq+wvbl#S9ie6h2_*CD)r(1m]:",
					"z!5R-b(o*+KGBOU3{cd;?£$QXkNMi:VI}0vT4%ap. _A]uPmle,8[fj1swqrh)E7gW6D#ZxJntLH=CyY2S9F",
					"CB{1TEiM_;#cJ,sA$az8 6w!K7-o%:4bYXP]UdLWFvVrN?Hh}eDR=uOg5S2).Zt90G(QkI+nm3y£l[pq*jfx",
					"ayn;D.fgo{t-H)Ob£,Z78p0CIrkT4i_GzlK%x}VRXMhAjFUJWdqLs N]!Q[3e#+m$2:S=*v5?wc(9EYB61uP",
					"$N5vn2Juye_UrMho;=iSWpACB THGRzF]IYXVZKL+a:Ekmts7!.(89c{6}l#-b£4jQ0),gODw?f31qxd%[P*",
					" _g+}zd6wUfY9rX[ou!S{;BGb]8cl#WN3DVmT0kOMARZ,P5hs$%j7yx£(q-IJHi42t.1LeE:n=QpKC*F)?av",
					"PN)13._SqCngGL07[#yJ(s%r9z8b4jU}:iQEv?A2FXDo*5YMTZBw£ufHt ahO;+-$pkKel=cx],dW{!R6VmI",
					"Zxa4f36lkPLT;e0igNrWD1X!Iz*#2{ 7.Q,Mc?[8RK£vwo$Jyn-:%EqbCY]+ABsGp_H)}Vduh9jt5=U(OFmS",
					"QH6=D*7YXF(ruy#n8f{jREloP}2Uz3hLM%KvtZ4!T)OB;b_a9£w5pk?Ai]:Vd.m+G, J-I[eNxCS$Wcg01qs",
					"COWM?}$YTU9()_uFQV#Sl=7Ggo3 {si-6eq[h4d%y;H1rawBDEcmn0:,kRJj+N].ZK!v2£IXz5t8Abf*xPpL",
					"[0p 7RDa-#Z+PBlgV2!1X3_(r){QndGw6K}s8%AIo,J5zCce:£YNuqTLUj4kf.v;H]iSWmM?tF=bxyh*9$OE",
					"DF4s}zEG.,xR!j5bt[d %-7+TXcrK1S0?;2yBHhf8LPqNouVvOa{liwU#p:I=Qe3$nkmCWZ6A(J]_Y£g*M9)",
					"RXB%gaZ+MisrfLC[{hUHo0Iv*!$u:W(E_yJV.QYDm25Okz7P=cp- xb3K8£Nq)9wGd1AT}t]l,n4FjeS;#?6",
					"n:KupCPTM*AH9rbvZ0)xBSmD}tOU#z1lJX+?[Qdfo,£s$eFI;ac_k68Y{R-23i=Ej.7q !G5(ygLNW]wV4h%",
					"SBvFbMzX4 oE[019Y=5g2V,K{-R)h;:LOA.pDu}PcN#H%ndyW6CqJjtTiwZ+ae!rkU7(Q?xG$]f*3_8l£Ims",
					"ow*uNF2C eVjRB7TDWnYKGXIScs}f{6)zUd.5QZ8=m!%;k£0l9+$,P?4]Ev:igJA(y-Hb#rhOtqp1xLMa_[3",
					"NUM=_bKjtl6d Q+4GP9yn[Ii:cprV;f.m5R0qsTxuZYC7v£W*gEJF{a}OS)13hX-%BwD#o8e(2]k?L,$!AzH",
					"HDBCO?6-Nt_Z:!RKivJeU;(W58wmhpM7 c0zfATa$.}L£SIuV[*FG23%,nrobd)k4{=]lys9j+XYgPQxE#q1",
					"xLR?.75$%oac]1Zfl96;vG(yBsMb2k:OND)t04nir!j#TF£_-[hgX3p+KSmCzQ=IVq}*eu8,Ad{wUYWH EJP",
					")a,Idtp7$]gwYv2:Ax£E{Oql(j#uc3mU_k!o;is.R90zTNCnJr-WZBy+VLGMb1H}6%?Q=54K*8 hDF[ePSXf",
					"W2ar$TJ6RSy-z=[G1upOmeUg*lj.IXvF9k}A;b?:LV0#,_sn8NwQ%fqd{t)oBhZ+MP DE]H7x3(5Yi£!cCK4",
					"Mmp:w(gLDPIz,#+Y2vid=OsKV)y.6*!$Qru1Uc T0jSRb3xXJ5ef?£{]78EFhN;-qnt[%9_B4ClkAZH}oGaW",
					"}v!R3*KZBs e2cbmt.,GW{UMH]IfO%d_P1)-y0JYwNT$n56=[7Q#u4DphX(+A?a8E9Cjlg:Vr£z;oFikxSLq",
					"d)0vR3A}DJ]:n?hF!16V,.YEmL8;M%obQ[+k S_pyWIzu$r5l=sCKHajq7NU9BeX4Tx{i2Pct-O*#fZgG(w£",
					"mzZlI$0M*_P?y-r1=qe u7spOFY%UW:B.{AhTvfX#Jt3,oNV}wRQ!a5(b4L£cx9;6i+[)2DSdkKjEHn]C8gG"
				};

	//===================================================================================
	public void debug(String msg){
		//System.out.println(msg);
	}
	//===================================================================================
	public void debug1(String msg){
		System.out.println(msg);
	}
    	//===================================================================================
	// read txt files from local folder and load into 2darray
    	public String readFile(String path)
    	{
		try
		{
			String xx = "";
			String txt = "";

			RandomAccessFile cp = new RandomAccessFile(path, "r");
			while ((xx=cp.readLine())!=null) txt += xx;
			cp.close();

			return txt;
		}
		catch (IOException ioe)
		{
        	    	debug("reading file IOException - "+ioe.getMessage());
	    		return null;
		}
		catch (Exception e)
		{
            		debug("reading files Exception - "+e.getMessage());
	    		return null;
		}
    	}

	//===================================================================================
	public int getKeyNum(String key){
		return (keyStr.indexOf(key));
	}
	//===================================================================================
	public String encrypt(String txt, String key){

		String ntxt = "";
		String oneChar = "";
		int i = 0;
		int j = 0;
		int pos = 0;
		try
		{
			debug("txtl="+txt.length()+" key="+key);
			for (int k = 0; k < key.length(); k++){
				j = getKeyNum(key.substring(k,k+1));
				if (j < 0) throw new Exception("key character not valid - "+key);
				for (i = 0; i < txt.length(); i++){
					oneChar = txt.substring(i,i+1);
					pos = baseStr.indexOf(oneChar);
					if (pos == -1) ntxt += oneChar; else ntxt += cypherArray[j].substring(pos,pos+1);
					j++;
					if (j > cypherArray.length - 1) j = 0;
				}
				txt = ntxt;
				ntxt = "";
			}
		}catch (Exception ex){
			debug("Excep - i="+i+" j="+j+" pos="+pos+" 1char="+oneChar+" "+ntxt+" "+ex.getMessage());
			return "";
		}
		return txt;
	}
	//===================================================================================
	public String decypher(String txt, String key){

		String ntxt = "";
		String oneChar = "";
		int i = 0;
		int j = 0;
		int pos = 0;
		try
		{
			debug("txtl="+txt.length());
			for (int k = key.length() - 1; k > -1 ; k--){
				j = getKeyNum(key.substring(k,k+1));
				if (j < 0) throw new Exception("key character not valid - "+key);
				for (i = 0; i < txt.length(); i++){
					oneChar = txt.substring(i,i+1);
					pos = cypherArray[j].indexOf(oneChar);
					if (pos == -1) ntxt += oneChar; else ntxt += baseStr.substring(pos,pos+1);
					j++;
					if (j > cypherArray.length - 1) j = 0;
				}
				txt = ntxt;
				ntxt = "";
			}
		}catch (Exception ex){
			debug("Excep - i="+i+" j="+j+" pos="+pos+" 1char="+oneChar+" "+ntxt+" "+ex.getMessage());
			return "";
		}
		return txt;
	}
	//===================================================================================
	public boolean validateArgs(String CorD, String fn, String key){
		boolean valid = true;

		if (CorD.equals("C")||CorD.equals("D")) valid = true; else return false;
		if (fn.indexOf(".") == -1) return false; 
		for (int k = key.length() - 1; k > -1 ; k--){
			if (getKeyNum(key.substring(k,k+1)) < 0){
				valid = false;
				break;
			}
		}
		return valid;
	}
    	//===================================================================================
    	public String CypherMain(String CorD, String fn, String key)
    	{
		String resultstr = "";
		Cypher100 cy = new Cypher100();
		if (cy.validateArgs(CorD, fn, key)){
			String xx = cy.readFile(fn);
			if (xx != null){
				debug("Processing "+fn);
				if (CorD.equals("C")){ 
					resultstr = cy.encrypt(xx,key);
				}
				if (CorD.equals("D")){ 
					resultstr = cy.decypher(xx,key);
				}
			}
		}else{
			resultstr = "Invalid arguments - <'C' or 'D'> <filename> <numeric key>";
		}
		return resultstr;
	}
    	//===================================================================================
    	static public void main(String[] args)
    	{
		String CorD = args[0];
		String fn = args[1];
		String key = args[2];
		Cypher100 cy = new Cypher100();
		String resultstr = cy.CypherMain(CorD, fn, key);
		System.out.println(resultstr);
	}
}
