package freepander.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页模型
 * 
 * @author freepander
 * 
 */
public class PageModel {

	/**
	 * 每页显示的数量
	 */
	private int countOfOnePage;
	/**
	 * 当前页
	 */
	private int pageNum;
	/**
	 * 总记录数
	 */
	private Long count;
	/**
	 * 总页数
	 */
	private Long countPage;
	/**
	 * 数据记录 List
	 */
	private List<Object> objects;
	/**
	 * 页数的list
	 */
	private List<Integer> pageNumList = new ArrayList<Integer>();
	/**
	 * 页面跳转到额url
	 */
	private String url;

	// get和set方法
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCount() {
		return count;
	}

	public int getCountOfOnePage() {
		return countOfOnePage;
	}

	public Long getCountPage() {
		return countPage;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public int getPageNum() {
		return pageNum;
	}

	public PageModel setCount(Long count) {
		this.countPage = count / this.getCountOfOnePage();
		if (count % this.getCountOfOnePage() > 0) {
			this.countPage++;
		}
		if (this.countPage == 0) {
			this.countPage = (long) 1;
		}
		this.count = count;
		this.generatePageList();
		return this;
	}

	public PageModel setCountOfOnePage(int countOfOnePage) {
		this.countOfOnePage = countOfOnePage;
		return this;
	}

	public PageModel setCountPage(Long countPage) {
		this.countPage = countPage;
		return this;
	}

	public PageModel setObjects(List<Object> objects) {
		this.objects = objects;
		return this;
	}

	public PageModel setPageNum(int pageNum) {
		this.pageNum = pageNum;
		return this;
	}

	public List<Integer> getPageNumList() {
		return pageNumList;
	}

	public void setPageNumList(List<Integer> pageNumList) {
		this.pageNumList = pageNumList;
	}

	public void generatePageList() {
		if (this.countPage <= 9) {
			for (int i = 1; i <= this.countPage; i++) {
				this.pageNumList.add(i);
			}
		} else {
			if (this.pageNum <= 9) {
				for (int i = 1; i <= 9; i++) {
					this.pageNumList.add(i);
				}
			} else if (this.pageNum >= this.countPage - 8) {
				for (int i = (int) (this.countPage - 8); i <= this.countPage; i++) {
					this.pageNumList.add(i);
				}
			} else {
				for (int i = (int) (this.countPage - 4); i <= this.countPage + 4; i++) {
					this.pageNumList.add(i);
				}
			}
		}

	}
}
