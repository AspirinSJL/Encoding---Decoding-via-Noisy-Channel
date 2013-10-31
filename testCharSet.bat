generateCharSet.exe

java encode74 charSet.txt charSetEncoded1.txt
java decode74 charSetEncoded1.txt charSetDecoded11.txt
java bscSIM charSetEncoded1.txt charSetTrans1.txt
java decode74 charSetTrans1.txt charSetDecoded12.txt

java encodeCustom charSet.txt charSetEncoded2.txt
java decodeCustom charSetEncoded2.txt charSetDecoded21.txt
java bscSIM charSetEncoded2.txt charSetTrans2.txt
java decodeCustom charSetTrans2.txt charSetDecoded22.txt

java encodeCombined charSet.txt charSetEncoded3.txt
java decodeCombined charSetEncoded3.txt charSetDecoded31.txt
java bscSIM charSetEncoded3.txt charSetTrans3.txt
java decodeCombined charSetTrans3.txt charSetDecoded32.txt
