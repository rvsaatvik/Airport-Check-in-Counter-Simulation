/*

Simple Java Program.
Can be compiled in Terminal.

All answers might not be solved. But all methods have been implemented.
Submitted on time. Improved Version shall be submitted later.

Vishnu Saatvik Ramisetty
vr276


 */





package Ass2;

import java.util.Random;
import java.util.Scanner;

public class Queue {
    private static final int capacity = 50;
    int arr[] = new int[capacity];
    int top = -1;
    int rear = 0;

    public void push(int pushedElement) {
        if (top < capacity - 1) {
            top++;
            arr[top] = pushedElement;
            //display();
        } else {
            System.out.println("Queue Overflow.");
        }

    }

    public int size()
    {
        return arr.length;
    }

    public int pop() {
        if (top >= rear) {
            rear++;
           //display();
        } else {
            System.out.println("Queue Underflow.");
        }
        return arr[rear];
    }

    public void display() {
        if (top >= rear) {
            System.out.println("Elements in Queue : ");
            for (int i = rear; i <= top; i++) {
                System.out.println(arr[i]);
            }
        }
    }



    public static void main(String[] args) {


        int passengerNumber = 0;
        
        Queue queueFirstClass = new Queue();
        Queue queueCoach = new Queue();

        int passengerArrivalTime;

        Random randomPassengerArrivalTime = new Random();
        Random randomServiceTime = new Random();
        Random randomPassengerType = new Random();

        int passengersTotalServiceTime = 0;

        int maximumServiceTimeFirstClass;
        int maximumServiceTimeCoach;
        int serviceTime;

        // QUEUE DEFINITIONS
        Queue FirstClassCounter1 = new Queue();
        Queue FirstClassCounter2 = new Queue();

        Queue FirstClassServiceTimeQueue = new Queue();
        Queue FirstClassArrivalTimeQueue = new Queue();
        Queue CoachServiceTimeQueue = new Queue();
        Queue CoachArrivalTimeQueue = new Queue();

        Queue CoachCounter1 = new Queue();
        Queue CoachCounter2 = new Queue();
        Queue CoachCounter3 = new Queue();

        float averageServiceTime;
        float maximumServiceTime;

        int FirstClassQCount;
        int CoachQCount;
        int maxQFirstClass;
        int maxQCoach;

        Scanner ip1 = new Scanner(System.in);
        System.out.println("Average Arrival Rate for First Class");
        double averageArrivalRateFirstClassR1 = Double.parseDouble(ip1.next());
        System.out.println("Average Arrival Rate for Coach ");
        double averageArrivalRateCoachR2 = Double.parseDouble(ip1.next());
        System.out.println("Experiment Duration");
        int duration = Integer.parseInt(ip1.next());
        System.out.println("Max Service Time for First Class (Min:1)");
        maximumServiceTimeFirstClass = Integer.parseInt(ip1.next());
        System.out.println("Max Service Time for Coach is (Min:1)");
        maximumServiceTimeCoach = Integer.parseInt(ip1.next());

        while (passengersTotalServiceTime < duration) {

            passengerNumber++;
            //System.out.print("Passenger Number : " + passengerNumber);
            //passengerArrivalTime = 1 + randomPassengerArrivalTime.nextInt(duration);
            if (randomPassengerType.nextInt(3) == 1)
            {
                //System.out.print("Passenger Type " + randomPassengerType);
                passengerArrivalTime = randomPassengerArrivalTime.nextInt((int) averageArrivalRateFirstClassR1);
                queueFirstClass.push(passengerNumber);
                if (maximumServiceTimeFirstClass == 1) serviceTime = 1;
                else
                    serviceTime = 1 + randomServiceTime.nextInt(maximumServiceTimeFirstClass);
                passengersTotalServiceTime = passengersTotalServiceTime + serviceTime;  
                //System.out.print("Passenger total time" + passengersTotalServiceTime);
                //queueFirstClass.push(serviceTime);
                //queueFirstClass.push(passengerArrivalTime);

                FirstClassServiceTimeQueue.push(serviceTime);
                FirstClassArrivalTimeQueue.push(passengerArrivalTime);

            }

            else
            {
                passengerArrivalTime = randomPassengerArrivalTime.nextInt((int) averageArrivalRateCoachR2);
                queueCoach.push(passengerNumber);  //loading in queue 1st INFO
                if (maximumServiceTimeCoach == 1) serviceTime = 1;
                else
                    serviceTime= randomServiceTime.nextInt(maximumServiceTimeCoach);
                    passengersTotalServiceTime = passengersTotalServiceTime + serviceTime;
                CoachServiceTimeQueue.push(serviceTime);
                CoachArrivalTimeQueue.push(passengerArrivalTime);
            }

        }
        //System.out.print("P Total Service" + passengersTotalServiceTime);
        //System.out.print("P Count " + passengerNumber);
        maxQFirstClass = (queueFirstClass.size()) / 3;
        maxQCoach = (queueCoach.size()) / 3;

  
        for (int passengerCounter = passengerNumber; passengerCounter >= 1; passengerCounter--)
        {
            if ((FirstClassCounter1.size() == 0) && (queueFirstClass.size() != 0))
            {
                //for (int counter = 3; counter >= 1; counter--)
                    FirstClassCounter1.push(queueFirstClass.pop());
            }
            else if ((FirstClassCounter2.size() == 0) && (queueFirstClass.size() != 0))
            {
                //for (int counter = 3; counter >= 1; counter--)
                    FirstClassCounter2.push(queueFirstClass.pop());
            }
            else if ((CoachCounter1.size() == 0) && (queueCoach.size() != 0))
            {
                //for (int counter = 3; counter >= 1; counter--)
                    CoachCounter1.push(queueCoach.pop());
            }
            else if ((CoachCounter2.size() == 0) && (queueCoach.size() != 0))
            {
                //for (int counter = 3; counter >= 1; counter--)
                    CoachCounter2.push(queueCoach.pop());
            }
            else if ((CoachCounter3.size() == 0) && (queueCoach.size() != 0))
            {
                //for (int counter = 3; counter >= 1; counter--)
                    CoachCounter3.push(queueCoach.pop());
            }
            else if (((FirstClassCounter1.size() == 0) && (queueFirstClass.size() == 0)) && (queueCoach.size() != 0))
            {
                //for (int counter = 3; counter >= 1; counter--)
                    FirstClassCounter1.push(queueCoach.pop());
            }
            else if (((FirstClassCounter2.size() == 0) && (queueFirstClass.size() == 0)) && (queueCoach.size() != 0))
            {
                //for (int counter = 3; counter >= 1; counter--)
                    FirstClassCounter2.push(queueCoach.pop());
            }
        }

        
        System.out.print("\n\n The Answers:");

        System.out.print("\n\n Total Number of Passengers Served: " + passengerNumber);

        averageServiceTime = (float)passengersTotalServiceTime / (float)passengerNumber;
        System.out.print("\n\n Average Service Time= " + averageServiceTime);


        if (maximumServiceTimeFirstClass >= maximumServiceTimeCoach)
            maximumServiceTime = maximumServiceTimeFirstClass;
        else
            maximumServiceTime = maximumServiceTimeCoach;
        System.out.print("\n\n Maximum Service Time= " + maximumServiceTime);


        /*FirstClassQCount = ((FirstClassCounter1.size()) / 3) + ((FirstClassCounter2.size()) / 3);
        //System.out.print("\n\n First Class Passengers Served= " + FirstClassQCount);


        CoachQCount = ((CoachCounter1.size()) / 3) + ((CoachCounter1.size()) / 3) + ((CoachCounter3.size()) / 3);
        int total = FirstClassQCount + CoachQCount;
        System.out.print("\n\n Total Passengers Served= " + total );
        */

        System.out.print("\n\n Time at the end of simulation: \n\n" + duration);

    }
}