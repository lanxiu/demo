public static CocurrentHashMap<String,AtomicInteger> countMap = new ConcurrentHashMap<>(100);


// 使用前必定存入或者加1
AtomicInteger ai = countMap.putIfAbsent(userId,new AtomicInteger(1));
boolean mapReplace =false;

// 如果替换不成功并且插入不成功，继续尝试
// 考虑改为 while(true) { if (插入判断） { } if(Replace判断） {} 的形式}

while(!mapReplace && ai != null) {
  AtomictInteger aiInc = new AtomicInteger(ai.get() + 1 );
   // 如果替换不成功，则继续尝试
   while(（mapReplace = countMap.replace(userId,ai,aiInc） == false) {
    ai = countMap.get(userId);
    // 替换不成功期间，发生数据被清空的情况，跳出替换
    if(ai == null) {
      break;
    }
    aiInc = new AtomicInteger(ai.get() + 1 );
   }

  // 数据被清空的情况下，重新插入，如果插入成功，则大循环结束，如果失败，则继续尝试替换
  if(ai == null&& mapReplace == false) {
    ai = countMap.putIfAbsent(userId,new AtomicInteger(1））；
    if（ai == null) {
      System.err.println("百万分之一概率能到达的分支 *_* ");
    }
  }
}

//**********************************

// 使用完成后必定减一
ai = countMap.get(userId);
AtomicInteger aiDecre = new AtomicInteger(ai.get() -1 );

// 减一操作必定发生在map内有数据的情况下
while(countMap.replace(userId,ai,aiDecre)) {
  ai = countMap.get(userId);
  aiDecre = new AtomicInteger(ai.get() - 1);
};

// 判断已经无人使用，即使未删除成功，也有其它的请求操作会处理这一部分
if（aiDecre.get() == 0) {
  countMap.remove(userId,aiDecre);
}









