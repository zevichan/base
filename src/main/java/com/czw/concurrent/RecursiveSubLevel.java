/*
package com.czw.concurrent;

*/
/**
 * 子类层级太多,根据ForkJoin实现的查询,4层子结构
 *
 * @author ZeviChen , 2017/6/22 17:48
 *//*

public class RecursiveSubLevel {

    public static void main(String[] args) {
        BigDecimal one = new BigDecimal(0);

        ForkJoinPool fjPool = new ForkJoinPool();
        Task task = new Task(0, oneUserList.size(), oneUserList, userService, orderService, one);
        fjPool.submit(task);
        Map<String, Object> data = task.get();
    }

}

class Task extends RecursiveTask<Map<String, Object>> {

    private static Logger log = Logger.getLoggerFactory(getClass());

    // 每个"小任务"最多只打印50个数
    private static final int MAX = 1;

    private int start;
    private int end;
    private List<UserRelation> userRelations;
    private IUserService userService;
    private IOrderService orderService;
    private BigDecimal point;

    Task(int start, int end, List<UserRelation> userRelations, IUserService userService, IOrderService orderService, BigDecimal point) {
        this.start = start;
        this.end = end;
        this.userRelations = userRelations;
        this.userService = userService;
        this.orderService = orderService;
        this.point = point;
    }

    @Override
    protected Map<String, Object> compute() {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        System.out.println("start : " + start + " , end : " + end);

        // 当end-start的值小于MAX时候，开始打印
        if ((end - start) <= MAX) {
            Map<String, Object> map = new HashMap<>();
            User user = userService.findOneById(userRelations.get(start).getId());
            if (user == null) {
                return null;
            }

            BigDecimal p = orderService.sumPoint(user.getId());
            if (null == p || p.doubleValue() < 0)
                p = new BigDecimal(0);
            point = point.add(p);

            map.put("userId", user.getUserId());
            map.put("username", user.getUsername());
            map.put("point", p);
            map.put("headImg", user.getHeadImg());
            list.add(map);
            rst.put("point", point);
            rst.put("list", list);
            return rst;
        } else {
            int middle = (start + end) / 2;
            Task left = new Task(start, middle, userRelations, userService, orderService, point);
            Task right = new Task(middle, end, userRelations, userService, orderService, point);

            invokeAll(left, right);

            Map<String, Object> lm = null;
            Map<String, Object> rm = null;
            try {
                lm = left.get();
            } catch (InterruptedException|ExecutionException e) {
                log.error(e.getMessage());
                return null;
            }

            try {
                rm = right.get();
            } catch (InterruptedException|ExecutionException e) {
                log.error(e.getMessage());
                return null;
            }

            if (lm != null && rm == null)
                return lm;
            if (lm == null && rm != null)
                return rm;

            if (lm == null && rm == null)
                return null;


            BigDecimal lp = (BigDecimal) lm.get("point");
            BigDecimal rp = (BigDecimal) rm.get("point");
            BigDecimal tp = new BigDecimal(0);

            List<Map<String, Object>> ll = (List<Map<String, Object>>) lm.get("list");
            List<Map<String, Object>> rl = (List<Map<String, Object>>) rm.get("list");
            List<Map<String, Object>> tl = new ArrayList<>();
            tl.addAll(ll);
            tl.addAll(rl);

            rst.put("point", tp.add(lp).add(rp));
            rst.put("list", tl);
            return rst;

        }
    }
}
*/
