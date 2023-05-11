// Using antlr4 to implement the formula:"\n${ \n  var temp = \"\"\n  var str = \"select * from EMPLOYEE where 1=1\" \n  str = str   \" and x = 3 \"\n  if (isNotEmpty(temp)) { \n    str = str   \" and DEPT_ID = 1\" \n  } \n  if (isNotEmpty(temp)) { \n    str = str   \" and EMPLOYEE_ID = 2\" \n  }  \n  if (isNotEmpty(temp)) { \n    str = str   \" and SEX = 1\" \n  } \n  return str; \n}"
 calculate the output: "select * from EMPLOYEE where 1=1 and x = 3 and DEPT_ID = 1 and EMPLOYEE_ID = 2 and SEX = 1"
import java.io.StringReader
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

object Antlr extends App {
  val formula =
    """\n${
      "  var temp = \"\"\n  var str = \"select * from EMPLOYEE where 1=1\" \n  str = str   \" and x = 3 \"\n  if (isNotEmpty(temp)) { \n    str = str   \" and DEPT_ID = 1\" \n  } \n  if (isNotEmpty(temp)) { \n    str = str   \" and EMPLOYEE_ID = 2\" \n  }  \n  if (isNotEmpty(temp)) { \n    str = str   \" and SEX = 1\" \n  }  \n"
    }"""
  val grammarText =
    """syntax root
      |{grammar Antlr;
      |option java.util.regex.Pattern commentPattern;
      |option java.util.regex.Pattern whitespacePattern;
      |option java.util.regex.Pattern variablePattern;
      |option java.util.regex.Pattern numberPattern;
      |option java.util.regex.Pattern stringPattern;
      |option java.util.regex.Pattern opPattern;
      |option java.util.regex.Pattern opAnd;
      |option java.util.regex.Pattern opOr;
      |option java.util.regex.Pattern opNot;
      |option java.util.regex.Pattern opMinus;
      |option java.util.regex.Pattern opPlus;
      |option java.util.regex.Pattern opMul;
      |option java.util.regex.Pattern opDiv;
      |option java.util.regex.Pattern opMod;
      |option java.util.regex.Pattern opEqual;
      |option java.util.regex.Pattern opNotEqual;
      |option java.util.regex.Pattern opLess;
      |option java.util.regex.Pattern opLessEqual;
      |option java.util.regex.Pattern opGreater;
      |option java.util.regex.Pattern opGreaterEqual;
      |option java.util.regex.Pattern opAndNot;
      |option java.util.regex.Pattern opOrNot;
      |option java.util.regex.Pattern opAssign;
      |option java.util.regex.Pattern opEqualEqual;
      |option java.util.regex.Pattern opNotEqualEqual;
      |option java.util.regex.Pattern opLessEqualEqual;
      |option java.util.regex.Pattern opGreaterEqualEqual;
      |option java.util.regex.Pattern opAdd;
      |option java.util.regex.Pattern opSub;
      |option java.util.regex.Pattern opMul;
      |option java.util.regex.Pattern opDiv;
      |option java.util.regex.Pattern opMod;
      |option java.util.regex.Pattern opAddEqual;
      |option java.util.regex.Pattern opSubEqual;
      |option java.util.regex.Pattern opMulEqual;
      |option java.util.regex.Pattern opDivEqual;
      |option java.util.regex.Pattern opModEqual;
      |option java.util.regex.Pattern opAddNotEqual;
      |option java.util.regex.Pattern opSubNotEqual;
      |option java.util.regex.Pattern opMulNotEqual;
      |option java.util.regex.Pattern opDivNotEqual;
      |option java.util.regex.Pattern opModNotEqual;
      |option java.util.regex.Pattern opNegate;
      |option java.util.regex.Pattern opNot;
      |option java.util.regex.Pattern opBracket;
      |option java.util.regex.Pattern opChar;
<h1><p>Bad Request</p></h1>