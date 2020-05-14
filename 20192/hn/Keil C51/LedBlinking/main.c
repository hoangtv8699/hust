#include <at89x51.h>
sbit LED_pin = P1^0;
bit LED_data = 0;
void delay(int interval){
int i,j;
for(i=0; i<255; i++){
for(j=0; j<interval; j++);
}
}
void main(){
while(1){
LED_pin = LED_data;
LED_data = ~LED_data;
delay(100); //Tre mot khoang thoi gian
}
}