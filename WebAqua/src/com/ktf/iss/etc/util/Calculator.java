package com.ktf.iss.etc.util;

import java.util.*;

public class Calculator
{
	private static int MAX_PRECISION;
	private Stack stack;
	private Cpu cpu;
	private Lexer lexer;
	private char token[];
	private String expression;
	private int precision;

	static 
	{
		StringBuffer sb = new StringBuffer();
		sb.append(0x7fffffffffffffffL);
		MAX_PRECISION = sb.length();
		sb.setLength(0);
		sb.append(0x8000000000000000L);
		if(sb.length() - 1 < MAX_PRECISION)
			MAX_PRECISION = sb.length() - 1;
		MAX_PRECISION--;
	}

    public Calculator(String expression)
    {
        this(expression, MAX_PRECISION / 2);
    }

    public Calculator(String expression, int precision)
    {
        stack = new Stack();
        cpu = new Cpu();
        token = null;
        setExpression(expression);
        setPrecision(precision);
    }

    public String evaluate()
    {
        stack.removeAllElements();
        lexer = new Lexer(expression);
        token = null;
        try
        {
            nextToken();
            parseLevel4();
            return new String((char[])stack.pop());
        }
        catch(EmptyStackException _ex)
        {
            throw new IllegalArgumentException("Empty Stack Error at position " + lexer.getLastPosition());
        }
    }

    public String getExpression()
    {
        return expression;
    }

    public int getPrecision()
    {
        return precision;
    }

    public static void main(String args[])
    {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < args.length; i++)
        {
            sb.append(args[i]);
            sb.append(' ');
        }

        Calculator calc = new Calculator(sb.toString());
        for(int i = 2; i < 18; i++)
        {
            calc.setPrecision(i);
            try
            {
                System.out.println(i + ": " + calc.evaluate());
            }
            catch(Exception e)
            {
                System.out.println(i + ": " + e.getClass().getName() + ": " + e.getMessage());
            }
        }

    }

    private final void nextToken()
    {
        token = lexer.getNextToken();
    }

    private void parseLevel0()
        throws EmptyStackException
    {
        if(token == null)
            throw new IllegalArgumentException("Incomplete Expression at position " + lexer.getLastPosition());
        if(token[0] >= '0' && token[0] <= '9' || token[0] == '.')
        {
            stack.push(token);
            nextToken();
        } else
        {
            throw new IllegalArgumentException("Unexpected token at position " + lexer.getLastPosition() + ": " + new String(token));
        }
    }

    private void parseLevel1()
        throws EmptyStackException
    {
        if(token == open)
        {
            nextToken();
            parseLevel4();
            if(token != close)
                throw new IllegalArgumentException("Missing ')' at position " + lexer.getLastPosition());
            nextToken();
        } else
        {
            parseLevel0();
        }
    }

    private void parseLevel2()
        throws EmptyStackException
    {
        if(token == plus || token == minus)
        {
            char op[] = token;
            nextToken();
            parseLevel1();
            if(op == minus)
                stack.push(cpu.setOp((char[])stack.pop()).negate());
        } else
        {
            parseLevel1();
        }
    }

    private void parseLevel3()
        throws EmptyStackException
    {
        parseLevel2();
        while(token == multiply || token == divide) 
        {
            char op[] = token;
            nextToken();
            parseLevel2();
            char operand2[] = (char[])stack.pop();
            char operand1[] = (char[])stack.pop();
            if(op == multiply)
                stack.push(cpu.setOp(operand1, operand2).multiply());
            else
                stack.push(cpu.setOp(operand1, operand2).divide());
        }
    }

    private void parseLevel4()
        throws EmptyStackException
    {
        parseLevel3();
        while(token == plus || token == minus) 
        {
            char op[] = token;
            nextToken();
            parseLevel3();
            char operand2[] = (char[])stack.pop();
            char operand1[] = (char[])stack.pop();
            if(op == plus)
                stack.push(cpu.setOp(operand1, operand2).add());
            else
                stack.push(cpu.setOp(operand1, operand2).subtract());
        }
    }

    public void setExpression(String expression)
    {
        if(expression == null)
        {
            throw new NullPointerException("Expression cannot be null");
        } else
        {
            this.expression = expression;
            return;
        }
    }

    public void setPrecision(int precision)
    {
        if(precision < 2)
            throw new IllegalArgumentException("Precision must be greater than 1");
        if(precision > MAX_PRECISION)
        {
            throw new IllegalArgumentException("Precision must be less than " + MAX_PRECISION);
        } else
        {
            this.precision = precision;
            return;
        }
    }

    private static final char plus[] = {
        '+'
    };
    private static final char minus[] = {
        '-'
    };
    private static final char multiply[] = {
        '*'
    };
    private static final char divide[] = {
        '/'
    };
    private static final char open[] = {
        '('
    };
    private static final char close[] = {
        ')'
    };

	class Lexer
	{

		int getLastPosition()
		{
			return last;
		}

		char[] getNextToken()
		{
			try
			{
				for(; buffer[cursor] == ' ' || buffer[cursor] == '\t' || buffer[cursor] == '\n' || buffer[cursor] == '\r'; cursor++);
				last = cursor;
				switch(buffer[cursor])
				{
				case 43: // '+'
					cursor++;
					return Calculator.plus;

				case 45: // '-'
					cursor++;
					return Calculator.minus;

				case 42: // '*'
					cursor++;
					return Calculator.multiply;

				case 47: // '/'
					cursor++;
					return Calculator.divide;

				case 40: // '('
					cursor++;
					return Calculator.open;

				case 41: // ')'
					cursor++;
					return Calculator.close;

				case 46: // '.'
				case 48: // '0'
				case 49: // '1'
				case 50: // '2'
				case 51: // '3'
				case 52: // '4'
				case 53: // '5'
				case 54: // '6'
				case 55: // '7'
				case 56: // '8'
				case 57: // '9'
					int begin = cursor;
					try
					{
						for(; buffer[cursor] >= '0' && buffer[cursor] <= '9'; cursor++);
						if(buffer[cursor] == '.')
							for(cursor++; buffer[cursor] >= '0' && buffer[cursor] <= '9'; cursor++);
						if(buffer[cursor] == 'e' || buffer[cursor] == 'E')
						{
							buffer[cursor] = 'e';
							cursor++;
							if(buffer[cursor] == '+' || buffer[cursor] == '-')
								cursor++;
							for(; buffer[cursor] >= '0' && buffer[cursor] <= '9'; cursor++);
						}
					}
					catch(ArrayIndexOutOfBoundsException _ex) { }
					char charar[] = new char[cursor - begin];
					System.arraycopy(buffer, begin, charar, 0, cursor - begin);
					return charar;

				case 44: // ','
				default:
					throw new IllegalArgumentException("Illegal character at position " + cursor + ": " + buffer[cursor]);
				}
			}
			catch(ArrayIndexOutOfBoundsException _ex)
			{
				return null;
			}
		}

		boolean hasMoreTokens()
		{
			return cursor < buffer.length;
		}

		private char buffer[];
		private int cursor;
		private int last;

		Lexer(String expression)
		{
			buffer = expression.toCharArray();
			cursor = last = 0;
		}
	}

	class Cpu
	{

		char[] add()
		{
			balancePowers();
			return format(base1 + base2, power1);
		}

		final void balancePowers()
		{
			if(power1 > power2)
			{
				if(base1 < 0L)
					for(; power1 != power2 && base1 * 10L > 0xf333333333333334L; power1--)
						base1 *= 10L;

				else
				if(base1 > 0L)
					for(; power1 != power2 && base1 * 10L < 0xcccccccccccccccL; power1--)
						base1 *= 10L;

				for(; power1 != power2; power2++)
					base2 /= 10L;

			}
			if(power2 > power1)
			{
				if(base2 < 0L)
					for(; power1 != power2 && base2 * 10L > 0xf333333333333334L; power2--)
						base2 *= 10L;

				else
				if(base2 > 0L)
					for(; power1 != power2 && base2 * 10L < 0xcccccccccccccccL; power2--)
						base2 *= 10L;

				for(; power1 != power2; power1++)
					base1 /= 10L;

			}
		}

		void debug()
		{
			System.out.println("base1 = " + base1);
			System.out.println("power1 = " + power1);
			System.out.println("base2 = " + base2);
			System.out.println("power2 = " + power2);
		}

		final void decodeInto1(char op[])
		{
			base1 = 0L;
			power1 = 0L;
			boolean count = false;
			int precisionleft = precision;
			for(int i = op[0] != '-' ? 0 : 1; i < op.length; i++)
			{
				if(op[i] >= '0' && op[i] <= '9')
				{
					if(precisionleft < 1)
					{
						if(precisionleft == 0 && op[i] > '4')
							base1++;
						if(!count)
							power1++;
					} else
					{
						if(count)
							power1--;
						base1 = base1 * 10L + (long)(op[i] - 48);
					}
					precisionleft--;
					continue;
				}
				if(op[i] == '.')
				{
					count = true;
					continue;
				}
				if(op[i] != 'e')
					continue;
				i++;
				boolean negate = op[i] == '-';
				if(op[i] == '-' || op[i] == '+')
					i++;
				long base = 0L;
				for(; i < op.length; i++)
					base = base * 10L + (long)(op[i] - 48);

				if(negate)
					base *= -1L;
				power1 += base;
				break;
			}

			if(op[0] == '-')
				base1 *= -1L;
			if(base1 == 0L)
				power1 = 0L;
		}

		char[] divide()
		{
			if(base1 < 0L)
				while(base1 * 10L > 0xf333333333333334L) 
				{
					base1 *= 10L;
					power1--;
				}
			else
			if(base1 > 0L)
				while(base1 * 10L < 0xcccccccccccccccL) 
				{
					base1 *= 10L;
					power1--;
				}
			return format(base1 / base2, power1 - power2);
		}

		char[] format(long base, long power)
		{
			if(base == 0L)
				power = 0L;
			StringBuffer sb = new StringBuffer();
			boolean neg = base < 0L;
			if(neg)
				base *= -1L;
			sb.append(base);
			if(sb.length() > precision)
			{
				boolean round = sb.charAt(precision) > '4';
				power += sb.length() - precision;
				sb.setLength(precision);
				if(round)
				{
					base = 0L;
					for(int i = 0; i < sb.length(); i++)
						base = base * 10L + (long)(sb.charAt(i) - 48);

					base++;
					sb.setLength(0);
					sb.append(base);
				}
			}
			if(power < 0L)
			{
				if(-1L * power < (long)sb.length())
				{
					sb.insert((int)((long)sb.length() + power), '.');
					power = 0L;
				} else
				if(-1L * power == (long)sb.length())
				{
					sb.insert(0, '0');
					sb.insert(1, '.');
					power = 0L;
				} else
				{
					power += sb.length() - 1;
					sb.insert(1, '.');
				}
				for(; sb.charAt(sb.length() - 1) == '0'; sb.setLength(sb.length() - 1));
				if(sb.charAt(sb.length() - 1) == '.')
					sb.setLength(sb.length() - 1);
			} else
			if(power > 0L)
				if((long)sb.length() + power <= (long)precision)
				{
					for(; power > 0L; power--)
						sb.append('0');

				} else
				{
					power += sb.length() - 1;
					sb.insert(1, '.');
					for(; sb.charAt(sb.length() - 1) == '0'; sb.setLength(sb.length() - 1));
					if(sb.charAt(sb.length() - 1) == '.')
						sb.setLength(sb.length() - 1);
				}
			if(power != 0L)
			{
				sb.append('e');
				sb.append(power);
			}
			if(neg)
				sb.insert(0, '-');
			char output[] = new char[sb.length()];
			sb.getChars(0, output.length, output, 0);
			return output;
		}

		char[] multiply()
		{
			return format(base1 * base2, power1 + power2);
		}

		char[] negate()
		{
			return format(-1L * base1, power1);
		}

		Cpu setOp(char op1[])
		{
			decodeInto1(op1);
			return this;
		}

		Cpu setOp(char op1[], char op2[])
		{
			decodeInto1(op2);
			base2 = base1;
			power2 = power1;
			decodeInto1(op1);
			return this;
		}

		char[] subtract()
		{
			balancePowers();
			return format(base1 - base2, power1);
		}

		long base1;
		long power1;
		long base2;
		long power2;

		Cpu()
		{
		}
	}
}
