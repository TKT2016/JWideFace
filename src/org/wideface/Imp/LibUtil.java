package org.wideface.Imp;

class LibUtil {

     public static int indexOf(String[] arr,String item,boolean ignoreCase)
     {
         if(ignoreCase)
         {
             return indexOfIgnoreCase(arr,item);
         }
         else
         {
             return indexOf(arr,item);
         }
     }

    public static int indexOf(String[] arr,String item)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(item.equals(arr[i]))
            {
                return i;
            }
        }
        return -1;
    }

     public static int indexOfIgnoreCase(String[] arr,String item)
     {
         String itemLower = item.toLowerCase();
         for(int i=0;i<arr.length;i++)
         {
             String arItemLower = arr[i].toLowerCase();
             if(itemLower.equals(arItemLower))
             {
                 return i;
             }
         }
         return -1;
     }

    /*public static <T> T[] toArray(List<?> list,Class<T> tClass)
    {
        int size = list.size();
        T[] array = new T[size];
        T[] arr = (T[])list.toArray(new T[size]);
    }*/
}
