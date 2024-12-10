package pocker;

public class Pocker {
    String[] a = new String[52];
    String[] b = new String[52];
    String[] c = new String[13];
    String[] d = new String[13];
    String[] e = new String[52];
    String[] name = {"fang", "hong", "mei", "hei"};
    int index , elength = 0;
    void 生成牌(){
        index = 0; //初始化索引为0
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= 13; j++){
                a[index] = name[i] + j + ".jpg";
                index++;
            }
        }
    }
    void 打印牌(String[] x){
        index = 0;  
        while (index < x.length) {
            System.out.print(x[index] + " ");
            if((index+1)%13==0)
                System.out.println("");
            index++;
        }
        System.out.println("");
        System.out.println("");
    }
    String[] 打乱牌(){
        int m,n;
        String tmp;
        String[] b = new String[52];
        System.arraycopy(a , 0 , b , 0 ,52);
        for(int i=0;i<100;i++){         //重复进行100次
        m=(int)Math.random()*52+1;        //强行转化为int型
        n=(int)(Math.random()*52);
        tmp = b[m];         //进行交换
        b[m] = b[n];
        b[n] = tmp;
        }
        //打印牌(b);
        return b;
    }
    String[] 抽取牌(String[] b){
        String[] c=new String[13];
        System.arraycopy(b, elength, c, 0, 13);
        //打印牌(c);
        return c;
    }
    void 排序牌(String[] c){
        //传入c排序得d，d是排序好的数组
        index = 0;
        for(int i=0;i<52;i++)
        {
            for(int j=0;j<13;j++)
                if(c[j] == a[i])
                {
                    d[index] = c[j];
                    c[j] = "null";
                    index++;
                }
        }
        
    }
    void 组合牌(String[] d){
        index = elength;
        for(int i=0;i<13;i++){
            e[index] = d[i];
            index++;
        }
        elength += 13;
    }
    
    String[] 获取牌(Pocker p){
        
        p.生成牌();
        //p.打印牌(p.a);
        p.b = p.打乱牌(); //得到打乱之后的数组b
        for(int i = 0; i < 4 ; i++)
        {
           p.c = p.抽取牌(p.b);
           p.排序牌(p.c);
           p.组合牌(p.d);
        }
        return e;
    }
}
