#include<iostream>
using namespace std;
int main()
{
	int N;
	cin>>N;
	int start=2*N;
	int sum=0;
	for(int i=start;i>=1;i-=2)/*Finding total elements*/
	{
		sum+=i;
	}
	int next=sum-N+1,next1=next;
	int first=1;
	int sub=N-1;
	for(int i=start;i>2;i-=2)
	{
		int star=start-i;
		for(int i=1;i<=star;i++)/*Adding stars*/
		cout<<"*";
		for(int j=1;j<=i/2;j++)
		{
			cout<<(first*10);
			first=first+1;
		}
		for(int k=1;k<=i/2;k++)
		{
			if(k==i/2)
			cout<<next1;
			else
			{
				cout<<(next1*10);
				next1=next1+1;
			}
		}
		cout<<endl;
		next=next-sub;
		sub--;
		next1=next;
	}
	int star=start-2;
	for(int i=1;i<=star;i++)
	{
		cout<<"*";
	}	
	cout<<first*10;
	first+=1;
	cout<<first;
}
