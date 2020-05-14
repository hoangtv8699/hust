;Common Anode 7SEG-LED numbers
NUM_0 EQU 40h ; code to display 0 on 7 segment
NUM_1 EQU 79h ; code to display 1 on 7 segment
NUM_2 EQU 24h ; code to display 2 on 7 segment
NUM_3 EQU 30h ; code to display 3 on 7 segment
NUM_4 EQU 19h ; code to display 4 on 7 segment
NUM_5 EQU 12h ; code to display 0 on 7 segment
NUM_6 EQU 02h ; code to display 1 on 7 segment
NUM_7 EQU 78h ; code to display 2 on 7 segment
NUM_8 EQU 00h ; code to display 3 on 7 segment
NUM_9 EQU 10h ; code to display 4 on 7 segment
;Interrupt table
ORG 0000h ; entry address for 8051 RESET
	LJMP MAIN ; move MAIN away from interrupt vector table
ORG 0003h ; vector address for interrupt 0
	LJMP ISR0 ; jump to ISR0
ORG 0013h ; vector address for interrupt 1
	LJMP ISR1 ; jump to ISR1
	ORG 0100h ; MAIN starts here
MAIN:
	MOV IE, #10000101B ; enable external interrupts IE0, IE1
	SETB IT0 ; negative edge trigger for interrupt 0
	SETB IT1 ; negative edge trigger for interrupt 1
	SETB P1.0 ; LED ON
LOOP:
	LJMP LOOP ; end loop
;==================================================================
; ISR0
;==================================================================
ISR0:
	SETB P1.0 ; LED ON
	RETI ; return from interrupt
;==================================================================
; ISR1
;==================================================================
ISR1:
	CLR P1.0 ; LED OFF
	RETI ; return from interrupt
	END ; end of program


NUM0:
	CJNE B, #0, NUM1
	MOV A, #NUM_0
	MOV P0, A
NUM1:
	CJNE B, #1, NUM2
	MOV A, #NUM_1
	MOV P0, A
NUM2:
	CJNE B, #2, NUM3
	MOV A, #NUM_2
	MOV P0, A
NUM3:
	CJNE B, #3, NUM4
	MOV A, #NUM_3
	MOV P0, A
NUM4:
	CJNE B, #4, NUM5
	MOV A, #NUM_4
	MOV P0, A

NUM5:
	CJNE B, #5, NUM6
	MOV A, #NUM_5
	MOV P0, A
NUM6:
	CJNE B, #6, NUM7
	MOV A, #NUM_6
	MOV P0, A
NUM7:
	CJNE A, #7, NUM8
	MOV A, #NUM_7
	MOV P0, A
NUM8:
	CJNE A, #8, NUM9
	MOV A, #NUM_8
	MOV P0, A
NUM9:
	MOV A, #NUM_9
	MOV P0, A
	