
import java.util.*;


public class Main {

    static boolean bool(int q) {
        boolean b = false;
        if (q == 1)
            b = true;
        if (q == 0)
            b = false;
        return (b);
    }

    public static void main(String[] args) {
        int maxvalue = 100;
        boolean debug = false;

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите количество балонов");
        int n = scan.nextInt();
        Queue<Balon> balons = new LinkedList<>();
        List<Balon> doneBalons = new ArrayList();
        List<Balon> needBalons = new ArrayList();
        List<Balon> donorBalons = new ArrayList();
        List<Balon> brokeBalons = new ArrayList();

        System.out.println("Введите последовательно ёмкость и состояние каждого балона(0-подлежит списыванию,1 - подлежит эксплуатации):");
        for (int i = 0; i < n; i++) {

            balons.add(new Balon(scan.nextInt(), bool(scan.nextInt())));

            if ((balons.peek().value == 100) && (balons.peek().status == true)) {
                doneBalons.add(balons.poll());
            }else
            if ((balons.peek().value < 100) && (balons.peek().status == true)) {
                needBalons.add(balons.poll());
            }else
            if ((balons.peek().value == 0) && (balons.peek().status == false)) {
                brokeBalons.add(balons.poll());
            }else
            if ((balons.peek().value != 0) && (balons.peek().status == false)) {
                donorBalons.add(balons.poll());
            }
        }

while((!needBalons.isEmpty()
        ||
        !donorBalons.isEmpty() ) && maxvalue>0
        ) {
    maxvalue--;
    for (int i = 0; i < donorBalons.size(); i++) {
        for (int j = 0; j < needBalons.size(); j++) {

            if ((needBalons.get(j).value) + (donorBalons.get(i).value) >= 100) {//
                int k = needBalons.get(j).value;
                donorBalons.get(i).value = ((needBalons.get(j).value) + (donorBalons.get(i).value)) - 100;
                needBalons.get(j).value = 100;
                needBalons.get(j).countDozs.add(100 - k);

                if (debug)
                    System.out.printf(">=100: Балон %s был дозаправлен из балона %s\n", needBalons.get(j).id, donorBalons.get(i).id);
                //System.out.println(donorBalons.get(i).value);
                needBalons.get(j).countDoz++;
                needBalons.get(j).balonsDozs.add(donorBalons.get(i).id);

            }

            if ((needBalons.get(j).value) + (donorBalons.get(i).value) < 100) {
                int k = needBalons.get(j).value;
                needBalons.get(j).value += (donorBalons.get(i).value);
                needBalons.get(j).countDozs.add(needBalons.get(j).value - k);
                donorBalons.get(i).value = 0;
                if (debug)
                    System.out.printf("<100: Балон %s был дозаправлен из балона %s\n", needBalons.get(j).id, donorBalons.get(i).id);
                //System.out.println(donorBalons.get(i).value);
                needBalons.get(j).countDoz++;
                needBalons.get(j).balonsDozs.add(donorBalons.get(i).id);
            }


            if (needBalons.get(j).value == 100) {
                doneBalons.add(needBalons.get(j));
                if (debug) System.out.printf("==100: Балон %s теперь готовый\n", needBalons.get(j).id);
                needBalons.remove(j);
            }
            if ((donorBalons.get(i).value == 0)) {
                brokeBalons.add(donorBalons.get(i));
                if (debug) System.out.printf("==0: Балон %s теперь сломанный\n", donorBalons.get(i).id);
                donorBalons.remove(i);
            }

        }
    }
}

if (!doneBalons.isEmpty()) {
    System.out.println("Дозаправленные:");
    for (int i = 0; i < doneBalons.size(); i++) {
        System.out.print("id: " + doneBalons.get(i).id + "\n\tОбъём: " + doneBalons.get(i).value +
                "\n\tКоличество дозаправок: " + doneBalons.get(i).countDoz);
        for (int j = 0; j < doneBalons.get(i).countDoz; j++) {
            System.out.printf("\n\tБалон %s был дозаправлен из балона %s на %s единиц",
                    doneBalons.get(i).id, doneBalons.get(i).balonsDozs.get(j), doneBalons.get(i).countDozs.get(j));
        }
        System.out.println();
    }
}
        if (!needBalons.isEmpty()) {
            System.out.println("Не до конца заправленные:");
            for (int i = 0; i < needBalons.size(); i++) {
                System.out.print("id: " + needBalons.get(i).id + "\n\tОбъём: " + needBalons.get(i).value +
                        "\n\tКоличество дозаправок: " + needBalons.get(i).countDoz);
                for (int j = 0; j < needBalons.get(i).countDoz; j++) {
                    System.out.printf("\n\tБалон %s был дозаправлен из балона %s на %s единиц",
                            needBalons.get(i).id, needBalons.get(i).balonsDozs.get(j), needBalons.get(i).countDozs.get(j));
                }
                System.out.println();
            }
        }
        if (!donorBalons.isEmpty()) {
            System.out.println("Списанные балоны в которых остался газ:");
            for (int i = 0; i < donorBalons.size(); i++) {
                System.out.println("id: " + donorBalons.get(i).id + "\n\tОбъём: " + donorBalons.get(i).value);
            }
        }
        if (!brokeBalons.isEmpty()) {
            System.out.println("Списанные:");
            for (int i = 0; i < brokeBalons.size(); i++) {
                System.out.println("id: " + brokeBalons.get(i).id);
            }
        }
    }
}


