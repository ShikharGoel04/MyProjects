#include<iostream>
using namespace std;
int main()
{
	long long N;
	
	cin>>N;
	
	while(N>=1 && N<=3999)
	{
		string result;
			long long num=N;
			long long X=num;
			int digc=0;
			while(X>0)
			{
				int d=X%10;
				digc++;
				X=X/10;
			}
			
			int count=digc;
			while(digc>0)
			{
				int dig;
				int th;
				th=1;
				for(int i=1;i<=(digc-1);i++)
				th*=10;
				
				dig=num/th;
				if(digc==1)
				{
					switch(dig)
					{
						case 1:
						{
							result+="I";
							break;
						}
						case 2:
						{
							result+="II";
							break;
						}
						case 3:
						{
							result+="III";
							break;
						}
						case 4:
						{
							result+="IV";
							break;
						}
						case 5:
						{
							result+="V";
							break;
						}
						case 6:
						{
							result+="VI";
							break;
						}
						case 7:
						{
							result+="VII";
							break;
						}
						case 8:
						{
							result+="VIII";
							break;
						}
						case 9:
						{
							result+="IX";
							break;
						}
					}
				th=1;
				for(int i=1;i<=(digc-1);i++)
				th*=10;
				num=num-(dig*th);	
				digc--;
					
			}
				else if(digc==2)
				{
					switch(dig)
					{
						case 1:
						{
							result+="X";
							break;
						}
						case 2:
						{
							result+="XX";
							break;
						}
						case 3:
						{
							result+="XXX";
							break;
						}
						case 4:
						{
							result+="XL";
							break;
						}
						case 5:
						{
							result+="L";
							break;
						}
						case 6:
						{
							result+="LX";
							break;
						}
						case 7:
						{
							result+="LXX";
							break;
						}
						case 8:
						{
							result+="LXXX";
							break;
						}
						case 9:
						{
							result+="XC";
							break;
						}
					}
				th=1;
				for(int i=1;i<=(digc-1);i++)
				th*=10;
				num=num-(dig*th);	
				digc--;
			}
			else if(digc==3)
			{
				switch(dig)
				{
					case 1:
					{
						result+="C";
						break;
					}
					case 2:
					{
						result+="CC";
						break;
					}
					case 3:
					{
						result+="CCC";
						break;
					}
					case 4:
					{
						result+="CD";
						break;
					}
					case 5:
					{
						result+="D";
						break;
					}
					case 6:
					{
						result+="DC";
						break;
					}
					case 7:
					{
						result+="DCC";
						break;
					}
					case 8:
					{
						result+="DCCC";
						break;
					}
					case 9:
					{
						result+="CM";
						break;
					}
				}
			th=1;
			for(int i=1;i<=(digc-1);i++)
			th*=10;
			num=num-(dig*th);	
			digc--;
			
		}
			else if(digc==4)
			{
				switch(dig)
				{
					case 1:
					{
						result+="M";
						break;
					}
					case 2:
					{
						result+="MM";
						break;
					}
					case 3:
					{
						result+="MMM";
						break;
					}
			}
		th=1;
		for(int i=1;i<=(digc-1);i++)
		th*=10;
		num=num-(dig*th);	
		digc--;
	}
	
}
int i=0;
char s=result[i];
int max=0;
/*finding the max*/
while(result[i])
{
	int k=(int)result[i];
	if(k>max)
	max=k;
	i++;
}
int base=max-65+11;
int length=i;

/*converting to base 10*/
			long long converted=0;
			int p=0;
			while(length>0)
			{
				long long th=1;
				for(int i=1;i<=(length-1);i++)
				th*=base;
				converted+=(((int)result[p++])-65+10)*th;
				length--;
			}
			N=converted;
	}
	cout<<N<<endl;
}
