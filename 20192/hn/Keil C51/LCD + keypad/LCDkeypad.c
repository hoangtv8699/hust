#include <at89x51.h>
#include "string.h"
#define KEYPAD_INTERRUPT 0
#define LCD_DATA P2
sbit EN=P0^2;
sbit RS=P0^0;
sbit RW=P0^1;

sbit HA=P1^0;
sbit HB=P1^1;
sbit HC=P1^2;
sbit HD=P1^3;
sbit CO1=P1^4;
sbit CO2=P1^5;
sbit CO3=P1^6;
sbit CO4=P1^7;
//Prototypes
void init_system();
void delay(int interval);
void output_7seg(unsigned char value);
unsigned char parse_key();
void Delay_ms(int interval);  
void LCD_init();
void Wait_For_LCD();
void LCD_Send_Command(unsigned char x);
void LCD_Write_One_Char(unsigned char c);
void LCD_Write_String(unsigned char *s);
//Global vars
unsigned char num=0;
//Main prog
void main()
{
	init_system();
	LCD_init();
	LCD_Write_String("Xin chao KTMT62");
	LCD_Send_Command(0xC0); //Chuyen con tro xuong dong thu 2	
	while(1){		
	}
}

void Init_System()
{
	IT0=1; 		//Edge-triggered int 0
	//Keypad interface
	P2=0xF0; 	//4 bit thap -> chan ra
			 	//4 bit cao -> chan vao
	IE=0x81;	//Enable EXT0
	//Thiet lap LCD o che do doc
	RW=1;	
}
//system initialization
void init_system()
{
	IT0=1; 		//Edge-triggered int 0
	//Keypad interface
	P2=0xF0; 	//4 bit thap -> chan ra
			 	//4 bit cao -> chan vao
	IE=0x81;	//Enable EXT0
}
//Chuong trinh tao do tre
void delay(int interval)
{
	int i,j;
	for(i=0;i<1000;i++)
	{
		for(j=0;j<interval;j++);
	}
}
//7-seg display
void output_7seg(unsigned char value)
{
	unsigned char const mask[10]={0x40, 0x79, 0x24, 0x30, 0x19, 0x12, 0x02, 0x78, 0x00, 0x10};
	if(value < 10){
		P0=mask[value];
	}		 
}
//Keypad scanning
unsigned char parse_key()
{	
	//row A
	HA=0; HB=HC=HD=1;	
	if(CO1==0){
		return 7;
	}else if(CO2==0){
		return 8;
	}else if(CO3==0){
		return 9;
	}
	//Row B
	HA=HC=HD=1; HB=0;
	if(CO1==0){
		return 4;
	}else if(CO2==0){
		return 5;
	}else if(CO3==0){
		return 6;
	}

	//Row C
	HA=HB=HD=1; HC=0;
		if(CO1==0){
		return 1;
	}else if(CO2==0){
		return 2;
	}else if(CO3==0){
		return 3;
	}

	//Row D
	HA=HB=HC=1; HD=0;
	if(CO1==0){
		return 1;
	}else if(CO2==0){
		return 0;
	}else if(CO3==0){
		return 3;
	}
}
//EXT0 for keypressed
void process_keypad() interrupt KEYPAD_INTERRUPT
{
	unsigned char key;
	EA  = 0; 			//Cam ngat
	key = parse_key(); 	//Do xem phim nao duoc nhan
	//Tra ve trang thai cu de cho phim moi duoc nhan
	HA = HB = HC = HD = 0;
	LCD_Write_One_Char(key + 0x30);	
	EA = 1; 			//Cho phep ngat tro lai
}

void Delay_ms(int interval)
{
	int i,j;
	for(i=0;i<1000;i++)
	{
		for(j=0;j<interval;j++);
	}
}
//Ham thuc hien gui mot lenh xuong LCD
void LCD_Send_Command(unsigned char x)
{
	LCD_DATA=x;
	RS=0; //Chon thanh ghi lenh
	RW=0; //De ghi du lieu
	EN=1;
	Delay_ms(1);
	EN=0;		
	Wait_For_LCD(); //Doi cho LCD san sang
	EN=1;		  
}
//Ham khoi tao LCD o che do ghi
//Ham kiem tra va cho den khi LCD san sang
void Wait_For_LCD()
{
	//Delay_By_Timer_0(80);
	Delay_ms(1);
}
void LCD_init()
{
	LCD_Send_Command(0x38); //Chon che do 8 bit, 2 hang cho LCD
	LCD_Send_Command(0x0E); //Bat hien thi, nhap nhay con tro	
	LCD_Send_Command(0x01); //Xoa man hinh	
	LCD_Send_Command(0x80); //Ve dau dong
	
}
//Ham de LCD hien thi mot ky tu
void LCD_Write_One_Char(unsigned char c)
{
	LCD_DATA=c; //Dua du lieu vao thanh ghi 
	RS=1; //Chon thanh ghi du lieu
	RW=0;
	EN=1;
	Delay_ms(1);	
	EN=0;
	Wait_For_LCD();	
	EN=1;
}
//Ham de LCD hien thi mot xau
void LCD_Write_String(unsigned char *s)
{
	unsigned char length;
	length=strlen(s); //Lay do dai xau
	while(length!=0)
	{
		LCD_Write_One_Char(*s); //Ghi ra LCD gia tri duoc tro boi con tro
		s++; //Tang con tro
		length--;	  		 
	}
}