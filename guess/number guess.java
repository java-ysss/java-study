import java.util.Scanner;
import java.util.Random;

class Main{
  public static void main(String[] args){
    Scanner scanner = new Scanner (System.in);
    Random rand = new Random();
    int bestScore = 1000;



while (true) {

  int min = 1;

  int max = levelSelect(scanner);
  int count = setCount(max);
  int startCount = count;
  
  int cpu = cpu(rand,max);

  while (count > 0) {

    int player = player(scanner,min,max);

    if (min > player || max < player) {
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("範囲内の数字を入力してください");
      continue;
    }

    count--;

    //int diff = Math.abs(cpu - player);
  
      if (cpu > player) {
      min = player + 1;
      System.out.println("もっと大きいです");
      System.out.println("----------------------------");
      System.out.println("残り回数" + count);
      continue;
    }else if (cpu < player) {
      max = player - 1;
      System.out.println("もっと小さいです");
      System.out.println("----------------------------");
      System.out.println("残り回数" + count );
      continue;
    }else{
      System.out.println("======================");
      System.out.println("正解です！");
      break;
    }
   
  }
  
  
  if (count == 0) {
    System.out.println("ゲームオーバー！＾＾");
    System.out.println("答えは： " + cpu + " でした");
  }else{
    System.out.println( "残り" + count + "回で当てました！");
    System.out.println("======================");
  }
    int score = startCount - count;

    if (score < bestScore) {
      bestScore = score;
      System.out.println("★  新記録です！");
    }
    System.out.println("今回の回数：" + score);
    System.out.println("最短記録：" + bestScore);


  System.out.print("もう一度遊びますか？ ( y or n ): ");
  String answer = scanner.next();
  if (answer.equalsIgnoreCase("n")) {
    break;
  }
 }
}

 public static int player(Scanner scanner, int min, int max){
  System.out.print( min + "~" +  max +  "の数字を当ててください  ");
  int number = scanner.nextInt();
  return number;
 }
 public static int cpu(Random rand, int max){
  return rand.nextInt(max) + 1;
 }

public static int levelSelect(Scanner scanner){
   
    int max = 100;
    System.out.println("難易度を選んでください");
    System.out.println("1 : Easy( 1 ~ 50 )");
    System.out.println("2 : Normal( 1 ~ 100 )");
    System.out.println("3 : hard( 1 ~ 1000 )");

    int level = scanner.nextInt();

    if (level == 1) {
      max = 50;
    }else if (level == 3) {
      max = 1000;
    }else{
      max = 100;
    }

    return max;
  }
  public static int setCount(int max){
    if (max == 50) {
      return 10;
    }else if (max == 1000) {
      return 5;
    }else{
      return 7;
    }
  }


  }

