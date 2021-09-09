import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;




public class HuffmanCoding {
    static int[] charCountAry = new int[256];
    static String[] charCode = new String[256];
    static linkedList list = new linkedList();
    static BinaryTree b = new BinaryTree(list.listHead);

    private static class treeNode{
    
    public String chStr;
    public int freq;
    String code;
    public treeNode left;
    public treeNode right;
    public treeNode next;
    
    public treeNode(String a, int b, String c, treeNode l, treeNode r, treeNode n){
        chStr = a;
        freq = b;
        code = c;
        left = l;
        right = r;
        next = n; 
        }

        public treeNode(char a, int b, String c, treeNode l, treeNode r, treeNode n){
            chStr =  String.valueOf(a);
            freq = b;
            code = c;
            left = l;
            right = r;
            next = n;
            }

   public void printNode(treeNode T, PrintWriter DebugFile){
        if((T.next != null) && (T.left!=null)){
            DebugFile.print("(" + T.chStr + "," + T.freq + "," + T.code + "," + T.next.chStr + "," + T.left.chStr + "," + T.right.chStr + ")");
        }
        else if((T.next==null) & (T.left !=null)){
            DebugFile.print("(" + T.chStr + "," + T.freq + "," + T.code + "," + "NULL" + "," + T.left.chStr + "," + T.right.chStr + ")");
        }
        else if((T.next!=null) && (T.left == null)){
            DebugFile.print("(" + T.chStr + "," + T.freq + "," + T.code + "," + T.next.chStr + "," + "NULL" + ", NULL" + ")");
        }
        else{
            DebugFile.print("(" + T.chStr + "," + T.freq + "," + T.code + "," + "NULL, NULL, NULL)");
        }
    }
}

private static class linkedList {
    treeNode listHead;
    linkedList list;

    linkedList(){
        treeNode dummy = new treeNode("dummy", 0, " ",  null, null, null);
        listHead = dummy;
    }
    /*
    linkedList(treeNode T){
        treeNode dummy = new treeNode("dummy", 0, " ",  null, null, null);
        listHead = dummy;
        listHead.next = T;

    }
    */
    public void insertNewNode(treeNode node,treeNode newNode){
      // treeNode node = listHead;
        while((node.next!=null) && (node.next.freq < newNode.freq)){
            node = node.next;
            }
            newNode.next = node.next;
            node.next = newNode;
    }

    void printList(treeNode T, PrintWriter a){
        a.print("listHead --> ");
        while(T != null){
            T.printNode(T, a);
            a.print("-->");
            T = T.next;
        }
        a.print("NULL");
        



    }
}


private static class BinaryTree {
    treeNode Root;
     
     BinaryTree(treeNode r){
         Root = r;
     }
 
     static  public boolean isLeaf(treeNode T){
         if(T.left == null && T.right == null) return true;
         else{
             return false;
         }
     }
 
      void preOrderTraversal(treeNode T, PrintWriter DebugFile){
        T = b.Root;
         if(isLeaf(T)){
            T.printNode(T, DebugFile);
         }
         else{
              T.printNode(T, DebugFile);
              preOrderTraversal(T.left, DebugFile);
              preOrderTraversal(T.right, DebugFile);
         }
     }
    
     
 
      void inOrderTraversal(treeNode T, PrintWriter DebugFile){
       //  T = b.Root;
         if(isLeaf(T)){
             T.printNode(T, DebugFile);
         }
         
         else{
             inOrderTraversal(T.left, DebugFile);
             T.printNode(T, DebugFile);
             inOrderTraversal(T.right, DebugFile);
            // T.printNode(T, DebugFile);
         }
     }
     
 
      void postOrderTraversal(treeNode T, PrintWriter DebugFile){
         T = b.Root;
         if(isLeaf(T)){
             T.printNode(T, DebugFile);
         }
         else{
             postOrderTraversal(T.left, DebugFile);
             postOrderTraversal(T.right, DebugFile);
             T.printNode(T, DebugFile);
         }
 
 
 
     }
 
 
 
 
 }

 


 
  
    static void computeCharCount(FileReader inFile,int[] charCountAry) throws IOException {
        

        int b =1;
        
    while ((b=inFile.read())!=-1) { 
        int index = b;
      charCountAry[index]++; 
    }

   
}

    static void printCountAry(int charCountAry[],PrintWriter DebugFile){
        int d =1;
        for(int i=0;i<256;i++){
            if(charCountAry[i]!=0){
                char ch = (char) i;
                DebugFile.print(ch + d +" " + charCountAry[i]);
                d++;
            }
        }
    }

    static void constructHuffmanLList(int charCountAry[], PrintWriter DebugFile){
        
      //  treeNode newN = new treeNode("dummy", 0, " ", null, null, null);
        int index =0;
        while(index <256){
        if(charCountAry[index] > 0){
            char chr = (char) index;
            int freq = charCountAry[index];
            treeNode newNode = new treeNode(chr, freq, " ", null, null, null);
            list.insertNewNode(list.listHead, newNode);
            list.printList(list.listHead, DebugFile);
        }
        index++;
    }
    }

    static void constructHuffmanBinTree(treeNode listHead, PrintWriter DebugFile){
       // int freq =0;
        
         

    while(listHead.next.next !=null){
        treeNode f = listHead.next;
        treeNode s = f.next;
        String chStr = f.chStr + s.chStr;
        int freq = f.freq + s.freq;
        treeNode newNode = new treeNode(chStr, freq, "", null, null, null);

        newNode.left = f;
        newNode.right = s;
        newNode.next = null;
        list.insertNewNode(listHead, newNode);
        //listHead.next = s.next;
        list.printList(listHead, DebugFile);
        listHead.next = s.next;
        }
       // treeNode Root = listHead.next;
        b.Root = listHead.next;
        //BinaryTree b = new BinaryTree(listHead.next);
    }

    static void constructCharCode(treeNode T, String code){
        if(T.left == null && T.right == null){
            T.code = code;
            int index = T.chStr.charAt(0);
            charCode[index] = code; 
        }
        else{
            constructCharCode(T.left, code+ "0");
            constructCharCode(T.right, code+ "1");
         }
         }

    static void Encode(FileReader orgFile, PrintWriter compFile) throws IOException{
        //PrintWriter DeBugFile;
       //  orgFile = new FileReader("data.txt");
       //  BufferedReader b = new BufferedReader(orgFile);
        int r;
        //System.out.println("test");
        while ((r = orgFile.read()) != -1) {
            
             char charIn = (char) r;
             int index = (int) charIn;
             String code = charCode[index];
             //DeBugFile.print(index + " " + code);
             compFile.print(code);
   
            }
           // orgFile.close();
            orgFile.close();
    }

    static void Decode(FileReader compFile2, PrintWriter deCompFile) throws IOException{
        treeNode spot = b.Root;
        
    // compFile2 = new FileReader("data.txt");
    //BufferedReader file = new BufferedReader(compFile2);
        int ch =0;
        char oneBit;

        while((ch = compFile2.read())!=-1){
           oneBit = (char) ch;
            if(spot.right == null && spot.left == null){
                deCompFile.print(spot.chStr);
                spot = b.Root;
            }
            if((char) oneBit == '0'){
                spot = spot.left;
               
            }
            else if((char) oneBit == '1'){
                spot = spot.right;
            }
            else {
                deCompFile.print("Error! The compress file contains invalid character!");
            }
        }
        compFile2.close();
     }

    static void userInterface() throws IOException{
      //  System.out.println("test");
        String nameOrg, nameCompress, nameDeCompress;
        char YesNo = 'N';
        InputStreamReader reader = new InputStreamReader(System.in);
        nameOrg = "";
        Scanner in = new Scanner(System.in);
      //Y  System.out.println("Do you want to encode a file?");

        while(YesNo == 'N'){
        System.out.println("Do you want to encode a file?");
        YesNo = (char) reader.read();
        if(YesNo == 'N'){   
            System.exit(0); 
         }
         else{
            
            System.out.println("What is the filename, without .txt?");
            nameOrg = in.nextLine();
            //File file = new File(nameOrg);
         }
             
             nameCompress = nameOrg + "_Compressed.txt";
             nameDeCompress = nameOrg + "_DeCompressed.txt";
            nameOrg = nameOrg + ".txt";

             FileReader orgFile= new FileReader(nameOrg);
             PrintWriter compFile = new PrintWriter(new File(nameCompress));
            
             
             
    
             Encode(orgFile, compFile);
             compFile.close();
            FileReader compFile2 = new FileReader(nameCompress);
            PrintWriter deCompFile = new PrintWriter(new File(nameDeCompress));

             Decode(compFile2, deCompFile); 
          //  deCompFile.print( );
            compFile2.close();
            orgFile.close();
            deCompFile.close();
             
             
             System.out.println("Do you want to encode a file?");
             YesNo = (char) reader.read();
            
    

}
in.close();

}


 



public static void main(String[] args) throws IOException{
    FileReader file = new FileReader(new File(args[0]));
    String NameDebugFile = args[0] + "_DeBug.txt";
    PrintWriter DebugFile = new PrintWriter(NameDebugFile);
    //PrintWriter DeBugFile
    b.Root = list.listHead;
    computeCharCount(file, charCountAry);
    printCountAry(charCountAry, DebugFile);
    constructHuffmanLList(charCountAry, DebugFile);
    constructHuffmanBinTree(b.Root, DebugFile);
    constructCharCode(b.Root, "");
    list.printList(b.Root, DebugFile);
    
      //b.preOrderTraversal(b.Root, DebugFile);
 
  
    //b.inOrderTraversal(b.Root, DebugFile);
    //b.postOrderTraversal(b.Root, DebugFile);

 

    userInterface();





}
}