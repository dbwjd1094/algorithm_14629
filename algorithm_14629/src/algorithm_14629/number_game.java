package algorithm_14629;

import java.util.Arrays;
import java.util.Scanner;

public class number_game {
	
	public static void main(String args[]) {
		long num;
		
		Scanner sc = new Scanner(System.in);
		num = sc.nextLong();
		
		new number().number_game(num);
		
	}
}

class number{
	public void number_game(long num) {
		String str[] = String.valueOf(num).split("");
		int array[] = new int[str.length];
		
		for(int i=0 ; i<array.length ; i++)
			array[i] = Integer.parseInt(str[i]);
		
		int used[] = new int[10];
		for(int i=0 ; i<10 ; i++)
			used[i] = -1;
		for(int i=0 ; i<array.length ; i++) {
			int temp = array[i];
			for(int j=i+1 ; j<array.length ; j++) {
				if(temp == array[j]) {
					int eval=0;
					int count = 0;
					for(int k=0; k<j ; k++) {
						for(int t=0;t<used.length;t++) {
							if(array[k]==used[t]) eval=1;
						}
						if(eval==0) {
							used[count]=array[k]; count++;
						}
						eval=0;
					}
					eval=0;
					int coun=0;
					int use[] = new int[10-count];
					for(int k=0 ; k<10 ; k++) {
						for(int t=0 ; t<count ; t++) {
							if(k==used[t]) eval=1;
						}
						if(eval==0) {
							use[coun]=k; coun++;
						}
						eval=0;
					}
					
					Arrays.sort(use);
					int min = use[0], max = use[use.length-1];
				
					int small = min,big = max;
					for(int k=0 ; k<use.length ; k++) {
						if(use[k]<array[j] && use[k]>=small) {
							small = use[k];							
						}
						else if(use[k]>array[j] && use[k]<=big) {
							big = use[k];
						}
					}
					if(big==min) min=min+1;
					if(small==max) max=max-1;
					
					if(j+1 != array.length) {
						int n1 = array[j]*10 + array[j+1]; //같은수랑 다음수까지
						int n2 = big*10+min;
						int n3 = small*10+max;
						int a1=n2-n1, a2=n1-n3;
						if(a1<0) a1=-a1;
						if(a2<0) a2=-a2;
						
						if(a1>a2) {
							array[j]=small;
							array[j+1]=max;
							
							for(int l=use.length-1 ; l>=0 ; l--) {
								for(int t=j+2 ; t<array.length ; t++) {
									if(use[l]!=small && use[l]!=max) {
										array[t]=use[l]; j++;
										break;
									} 
								}
							}
						}
						else {
							array[j]=big;
							array[j+1]=min;
							
							for(int l=0 ; l<use.length ; l++) {
								for(int t=j+2; t<array.length; t++) {
									if(use[l]!=big && use[l]!=min) {
										array[t]=use[l]; j++; 
										break; 
									}
								}
							}							
						}
					}
					else {
						int n1 = array[j];
						if(big-n1>n1-small) {
							array[j]=small;
						}
						else
							array[j]=big;
					}
				
				}
			}
		}
		
		for(int i=0 ; i<array.length ; i++) {
			System.out.print(array[i]);
		}
	}
}
