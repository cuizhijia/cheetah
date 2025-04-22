package com.cheetah.algorihm;

import java.util.List;

// TODO: complete this object/class

public class PaginationHelper<I> {

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */

    private final int totalCount;

    private final int itemsPerPage;

    private final int pageCount;

    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this(collection.size(),itemsPerPage);
    }

    public PaginationHelper(int totalCount, int itemsPerPage) {
        this.totalCount = totalCount;
        this.itemsPerPage = itemsPerPage;
        pageCount = (int)Math.ceil(totalCount /(double) itemsPerPage);
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return totalCount;
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return pageCount;
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        final int lastPageIndex = pageCount - 1;
        if(pageIndex < 0 || pageIndex > lastPageIndex) {
            return -1;
        }
        return pageIndex < lastPageIndex ? itemsPerPage : totalCount - itemsPerPage * lastPageIndex;
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if(itemIndex >= totalCount || itemIndex < 0) {
            return -1;
        }
        return itemIndex / itemsPerPage;
    }

    @Override
    public String toString() {
        return "pageCount: " + pageCount() + " itemCount: " + itemCount() ;
    }

    private static final List<Character> collection = List.of('a', 'b', 'c', 'd', 'e', 'f');
    private static final PaginationHelper<Character> helper = new PaginationHelper<>(collection, 4);

    public static void main(String[] args) {
        System.out.println(((int) Math.ceil((double) 5 + 1 / 4) - 1));
        System.out.println(helper.toString());
        System.out.println(helper.pageIndex(0));
        System.out.println(helper.pageIndex(1));
        System.out.println(helper.pageIndex(2));
        System.out.println(helper.pageIndex(3));
        System.out.println(helper.pageIndex(4));
        System.out.println(helper.pageIndex(5));
        System.out.println(helper.pageIndex(6));
        System.out.println(helper.pageIndex(7));
        System.out.println(helper.pageIndex(10));

        System.out.println("------------------------------");
        System.out.println(helper.pageItemCount(0));
        System.out.println(helper.pageItemCount(1));
        System.out.println(helper.pageItemCount(2));
        System.out.println(helper.pageItemCount(3));
        System.out.println(helper.pageItemCount(4));
        System.out.println(helper.pageItemCount(5));
        System.out.println(helper.pageItemCount(6));
        System.out.println(helper.pageItemCount(10));



    }



}
