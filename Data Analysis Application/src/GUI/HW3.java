package GUI;

import javax.swing.*;
import java.awt.Component;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;

@SuppressWarnings("serial")
public class HW3 extends JFrame {

	//---------------Declare GUI Components---------------------------------------------	
	private JTabbedPane tabbedPane;
	private JPanel panelBusiness;
	private JPanel panelUser;
	private JPanel panelMainCat;
	private JScrollPane scrSubCat;
	private JPanel panelSubCat;
	private JScrollPane scrAttributes;
	private JPanel panelAttributes;
	private JPanel panelBusinessReview;
	private JScrollPane scrMainCat;
	private JLabel lblBusinessFrom;
	private JDateChooser cmbBusinessFromDate;
	private JLabel lblBusinessTo;
	private JDateChooser cmbBusinessToDate;
	private JLabel lblBusinessStars;
	private JComboBox<String> cmbBusinessStar;
	private JLabel lblBusinessStarValue;
	private JTextField txtBusinessStarValue;
	private JLabel lblBusinessVotes ;
	private JComboBox<String> cmbBusinessVotes;
	private JLabel lblBusinessVotesValue;
	private JTextField txtBusinessVotesValue;
	private JPanel panelBusinessQuery;
	private JScrollPane scrBusinessQuery;
	private JTextArea txtBusinessQuery;
	private JButton btnBusinessExecute ;
	private JScrollPane scrBusinessResults;
	private JLabel lblBusinessAndOr;
	private JComboBox<String> cmbBusinessAndOr;
	private JPanel panelUserSearch;
	private JLabel lblMemberSince;
	private JDateChooser cmbMemberSince;
	private JLabel lblReviewCount;
	private JComboBox<String> cmbReviewCount;
	private JLabel lblReviewCountValue;
	private JTextField txtReviewCountValue;
	private JLabel lblFriends;
	private JComboBox<String> cmbFriends;
	private JLabel lblFriendsValue;
	private JTextField txtFriendsValue;
	private JLabel lblAvegareStars;
	private JComboBox<String> cmbAverageStars;
	private JLabel lblAverageStarsValue;
	private JTextField txtAverageStarsValue;
	private JLabel lblVotes;
	private JComboBox<String> cmbVotes;
	private JLabel lblVotesValue;
	private JTextField txtVotesValue;
	private JPanel panelUserQuery;
	private JLabel lblUserAndOr;
	private JComboBox<String> cmbUserAndOr;
	private JTextArea txtUserQuery;
	private JButton btnUserQuery;
	private JScrollPane srcUserResults;
	private JScrollPane srcUserReviews;
	private PreparedStatement psQuery;
	private JTable tblBusinessResults;
	@SuppressWarnings("unused")
	private DefaultTableModel modelBusinessResults;
	private JCheckBoxList chkMainCategory;
	private DefaultListModel<JCheckBox> modelMainCat = new DefaultListModel<JCheckBox>();
	private JCheckBoxList chkSubCategory;
	private DefaultListModel<JCheckBox> modelSubCat = new DefaultListModel<JCheckBox>();
	private String strAndOr = "INTERSECT";
	private ArrayList<String> arrCheckedMainCat = new ArrayList<String>();
	private ArrayList<String> arrCheckedSubCat = new ArrayList<String>();
	private DefaultListModel<JCheckBox> modelAttributes = new DefaultListModel<JCheckBox>();
	private JCheckBoxList chkAttributes;
	private ArrayList<String> arrCheckedAttributes = new ArrayList<String>();
	private JScrollPane scrBusinessReviews;
	private JTable tblBusinessReviews;
	@SuppressWarnings("unused")
	private DefaultTableModel modelBusinessReviews;
	private JScrollPane scrUserQuery; 
	private JButton btnBuildQuery;
	private DefaultTableModel modelUserResults;
	private JTable tblUserResults;
	private ListSelectionListener slUserResults;
	private DefaultTableModel modelUserReviews;
	private JTable tblUserReviews;
	private ListSelectionListener slBusinessResults;



	public HW3() {
		setTitle("Data Analysis Application");
		initializeInterface();
		populateMainCategory();
		txtBusinessQuery.setText("");
	}

	//-------------Method is to Initialize GUI-------------------------------------------------------------------------------------------------
	private void initializeInterface() {
		getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 1896, 1027);
		getContentPane().add(tabbedPane);

		//--------------------------Business Tab ---------------------------------------------------------------
		panelBusiness = new JPanel();

		tabbedPane.addTab("Business Search", panelBusiness);
		panelBusiness.setLayout(null);


		scrMainCat = new JScrollPane();
		scrMainCat.setBounds(33, 40, 369, 468);
		panelBusiness.add(scrMainCat);

		panelMainCat = new JPanel();
		panelMainCat.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Main Category", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrMainCat.setViewportView(panelMainCat);
		panelMainCat.setLayout(new BoxLayout(panelMainCat, BoxLayout.LINE_AXIS));

		chkMainCategory = new JCheckBoxList();
		panelMainCat.add(chkMainCategory);
		chkMainCategory.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mEvent) {
				mainClicked(mEvent);
			}
		});

		scrSubCat = new JScrollPane();
		scrSubCat.setBounds(417, 40, 369, 468);
		panelBusiness.add(scrSubCat);

		panelSubCat = new JPanel();
		panelSubCat.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Sub Category", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrSubCat.setViewportView(panelSubCat);
		panelSubCat.setLayout(new BoxLayout(panelSubCat, BoxLayout.LINE_AXIS));

		chkSubCategory = new JCheckBoxList();
		panelSubCat.add(chkSubCategory);
		chkSubCategory.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mEvent) {
				subClicked(mEvent);
			}
		});

		scrAttributes = new JScrollPane();
		scrAttributes.setBounds(801, 40, 369, 468);
		panelBusiness.add(scrAttributes);

		panelAttributes = new JPanel();
		panelAttributes.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrAttributes.setViewportView(panelAttributes);
		panelAttributes.setLayout(new BoxLayout(panelAttributes, BoxLayout.LINE_AXIS));

		chkAttributes = new JCheckBoxList();
		panelAttributes.add(chkAttributes);
		chkAttributes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mEvent) {
				attClicked(mEvent);
			}
		});

		panelBusinessReview = new JPanel();
		panelBusinessReview.setBounds(33, 524, 1137, 123);
		panelBusinessReview.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Reviews", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelBusiness.add(panelBusinessReview);
		panelBusinessReview.setLayout(null);

		lblBusinessFrom = new JLabel("From:");
		lblBusinessFrom.setBounds(29, 28, 105, 30);
		panelBusinessReview.add(lblBusinessFrom);

		cmbBusinessFromDate = new JDateChooser();
		cmbBusinessFromDate.setDateFormatString("yyyy-MM-dd");
		cmbBusinessFromDate.setBounds(149, 28, 105, 30);
		panelBusinessReview.add(cmbBusinessFromDate);

		lblBusinessTo = new JLabel("To:");
		lblBusinessTo.setBounds(29, 74, 105, 30);
		panelBusinessReview.add(lblBusinessTo);

		cmbBusinessToDate = new JDateChooser();
		cmbBusinessToDate.setDateFormatString("yyyy-MM-dd");
		cmbBusinessToDate.setBounds(149, 74, 105, 30);
		panelBusinessReview.add(cmbBusinessToDate);

		lblBusinessStars = new JLabel("Star:");
		lblBusinessStars.setBounds(387, 28, 105, 30);
		panelBusinessReview.add(lblBusinessStars);

		cmbBusinessStar = new JComboBox<String>();
		cmbBusinessStar.setBounds(507, 30, 105, 30);
		cmbBusinessStar.setModel(new DefaultComboBoxModel<String>(new String[] { "=", "<", ">", "<=", ">=" }));
		panelBusinessReview.add(cmbBusinessStar);

		lblBusinessStarValue = new JLabel("Value:");
		lblBusinessStarValue.setBounds(387, 74, 105, 30);
		panelBusinessReview.add(lblBusinessStarValue);

		txtBusinessStarValue = new JTextField();
		txtBusinessStarValue.setBounds(507, 78, 105, 30);
		panelBusinessReview.add(txtBusinessStarValue);
		txtBusinessStarValue.setColumns(10);

		lblBusinessVotes = new JLabel("Votes:");
		lblBusinessVotes.setBounds(775, 28, 105, 30);
		panelBusinessReview.add(lblBusinessVotes);

		cmbBusinessVotes = new JComboBox<String>();
		cmbBusinessVotes.setBounds(900, 30, 105, 30);
		cmbBusinessVotes.setModel(new DefaultComboBoxModel<String>(new String[] { "=", "<", ">", "<=", ">=" }));
		panelBusinessReview.add(cmbBusinessVotes);

		lblBusinessVotesValue = new JLabel("Value:");
		lblBusinessVotesValue.setBounds(775, 74, 105, 30);
		panelBusinessReview.add(lblBusinessVotesValue);

		txtBusinessVotesValue = new JTextField();
		txtBusinessVotesValue.setBounds(900, 74, 105, 30);
		panelBusinessReview.add(txtBusinessVotesValue);
		txtBusinessVotesValue.setColumns(10);

		panelBusinessQuery = new JPanel();
		panelBusinessQuery.setBounds(33, 663, 1137, 285);
		panelBusinessQuery.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Query", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBusiness.add(panelBusinessQuery);
		panelBusinessQuery.setLayout(null);

		btnBusinessExecute = new JButton("Execute Query");
		btnBusinessExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aEvent) {
				//call method to populate results
				populateResults(aEvent);
			}
		});
		btnBusinessExecute.setBounds(969, 151, 140, 30);
		panelBusinessQuery.add(btnBusinessExecute);

		lblBusinessAndOr = new JLabel("Search for:");
		lblBusinessAndOr.setBounds(95, 34, 120, 30);
		panelBusinessQuery.add(lblBusinessAndOr);

		cmbBusinessAndOr = new JComboBox<String>();
		cmbBusinessAndOr.setBounds(230, 34, 340, 30);
		cmbBusinessAndOr.setModel(new DefaultComboBoxModel<String>(new String[] { "AND", "OR" }));
		panelBusinessQuery.add(cmbBusinessAndOr);
		cmbBusinessAndOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aEvent) {
				selectAndOr(aEvent);
			}
		});

		scrBusinessQuery = new JScrollPane();
		scrBusinessQuery.setBounds(29, 75, 925, 194);
		panelBusinessQuery.add(scrBusinessQuery);

		txtBusinessQuery = new JTextArea();
		txtBusinessQuery.setFont(new Font("Calibri", Font.BOLD, 18));
		scrBusinessQuery.setViewportView(txtBusinessQuery);
		txtBusinessQuery.setEditable(false);

		scrBusinessResults = new JScrollPane();
		scrBusinessResults.setBounds(1185, 40, 672, 468);
		panelBusiness.add(scrBusinessResults);

		modelBusinessResults = new DefaultTableModel();
		tblBusinessResults = new JTable();
		tblBusinessResults.setBackground(Color.LIGHT_GRAY);
		modelBusinessResults.addColumn("Business Id");
		modelBusinessResults.addColumn("Business Name");
		modelBusinessResults.addColumn("City");
		modelBusinessResults.addColumn("State");
		modelBusinessResults.addColumn("Stars");
		tblBusinessResults.setModel(modelBusinessResults);
		tblBusinessResults.setBounds(1185, 40, 672, 746);
		scrBusinessResults.setViewportView(tblBusinessResults);
		tblBusinessResults.removeColumn(tblBusinessResults.getColumnModel().getColumn(0));
		slBusinessResults = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lEvent) {
				populateBusinessReviews(lEvent);
			}	
		};
		tblBusinessResults.getSelectionModel().addListSelectionListener(slBusinessResults);

		scrBusinessReviews = new JScrollPane();
		scrBusinessReviews.setBounds(1185, 532, 672, 416);
		panelBusiness.add(scrBusinessReviews);

		modelBusinessReviews = new DefaultTableModel();
		tblBusinessReviews = new JTable();
		tblBusinessReviews.setBackground(Color.LIGHT_GRAY);
		modelBusinessReviews.addColumn("UserName");
		modelBusinessReviews.addColumn("Review Date");
		modelBusinessReviews.addColumn("Review Text");
		modelBusinessReviews.addColumn("Stars");
		modelBusinessReviews.addColumn("No. of Votes");
		tblBusinessReviews.setModel(modelBusinessReviews);
		tblBusinessReviews.setBounds(1185, 40, 672, 746);
		scrBusinessReviews.setViewportView(tblBusinessReviews);



		//--------------------User Tab------------------------------------------------------------------------------------------------------		
		panelUser = new JPanel();
		tabbedPane.addTab("User Search", panelUser);
		panelUser.setLayout(null);

		panelUserSearch = new JPanel();
		panelUserSearch.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Users", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUserSearch.setBounds(36, 36, 851, 491);
		panelUser.add(panelUserSearch);
		panelUserSearch.setLayout(null);

		lblMemberSince = new JLabel("Member Since:");
		lblMemberSince.setBounds(58, 55, 115, 30);
		panelUserSearch.add(lblMemberSince);

		cmbMemberSince = new JDateChooser();
		cmbMemberSince.setDateFormatString("yyyy-MM");
		cmbMemberSince.setBounds(188, 55, 150, 30);
		panelUserSearch.add(cmbMemberSince);

		lblReviewCount = new JLabel("Review Count:");
		lblReviewCount.setBounds(58, 130, 115, 30);
		panelUserSearch.add(lblReviewCount);

		cmbReviewCount = new JComboBox<String>();
		cmbReviewCount.setBounds(188, 130, 150, 30);
		cmbReviewCount.setModel(new DefaultComboBoxModel<String>(new String[] { "=", "<", ">", "<=", ">=" }));
		panelUserSearch.add(cmbReviewCount);

		lblReviewCountValue = new JLabel("Value:");
		lblReviewCountValue.setBounds(515, 135, 105, 30);
		panelUserSearch.add(lblReviewCountValue);

		txtReviewCountValue = new JTextField();
		txtReviewCountValue.setBounds(632, 137, 150, 30);
		panelUserSearch.add(txtReviewCountValue);
		txtReviewCountValue.setColumns(10);

		lblFriends = new JLabel("No. of Friends:");
		lblFriends.setBounds(58, 205, 115, 30);
		panelUserSearch.add(lblFriends);

		cmbFriends = new JComboBox<String>();
		cmbFriends.setBounds(188, 207, 150, 30);
		cmbFriends.setModel(new DefaultComboBoxModel<String>(new String[] { "=", "<", ">", "<=", ">=" }));
		panelUserSearch.add(cmbFriends);

		lblFriendsValue = new JLabel("Value:");
		lblFriendsValue.setBounds(515, 205, 105, 30);
		panelUserSearch.add(lblFriendsValue);

		txtFriendsValue = new JTextField();
		txtFriendsValue.setBounds(632, 207, 150, 30);
		panelUserSearch.add(txtFriendsValue);
		txtFriendsValue.setColumns(10);

		lblAvegareStars = new JLabel("Average Stars:");
		lblAvegareStars.setBounds(58, 276, 115, 30);
		panelUserSearch.add(lblAvegareStars);

		cmbAverageStars = new JComboBox<String>();
		cmbAverageStars.setBounds(188, 278, 150, 30);
		cmbAverageStars.setModel(new DefaultComboBoxModel<String>(new String[] { "=", "<", ">", "<=", ">=" }));
		panelUserSearch.add(cmbAverageStars);

		lblAverageStarsValue = new JLabel("Value:");
		lblAverageStarsValue.setBounds(515, 281, 105, 30);
		panelUserSearch.add(lblAverageStarsValue);

		txtAverageStarsValue = new JTextField();
		txtAverageStarsValue.setBounds(632, 280, 150, 30);
		panelUserSearch.add(txtAverageStarsValue);
		txtAverageStarsValue.setColumns(10);

		lblVotes = new JLabel("No. of Votes:");
		lblVotes.setBounds(58, 347, 115, 30);
		panelUserSearch.add(lblVotes);

		cmbVotes = new JComboBox<String>();
		cmbVotes.setBounds(188, 349, 150, 30);
		cmbVotes.setModel(new DefaultComboBoxModel<String>(new String[] { "=", "<", ">", "<=", ">=" }));
		panelUserSearch.add(cmbVotes);

		lblVotesValue = new JLabel("Value:");
		lblVotesValue.setBounds(515, 352, 105, 30);
		panelUserSearch.add(lblVotesValue);

		txtVotesValue = new JTextField();
		txtVotesValue.setBounds(632, 351, 150, 30);
		panelUserSearch.add(txtVotesValue);
		txtVotesValue.setColumns(10);

		lblUserAndOr = new JLabel("Search for:");
		lblUserAndOr.setBounds(58, 423, 105, 30);
		panelUserSearch.add(lblUserAndOr);

		cmbUserAndOr = new JComboBox<String>();
		cmbUserAndOr.setBounds(188, 423, 150, 30);
		panelUserSearch.add(cmbUserAndOr);
		cmbUserAndOr.setModel(new DefaultComboBoxModel<String>(new String[] { "AND", "OR" }));

		btnBuildQuery = new JButton("Build Query");
		btnBuildQuery.setBounds(587, 423, 130, 30);
		panelUserSearch.add(btnBuildQuery);
		btnBuildQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aEvent) {
				buildUserQuery(aEvent);
			}
		});

		panelUserQuery = new JPanel();
		panelUserQuery.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Query", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUserQuery.setBounds(36, 565, 851, 314);
		panelUser.add(panelUserQuery);
		panelUserQuery.setLayout(null);

		btnUserQuery = new JButton("Execute Query");
		btnUserQuery.setBounds(681, 155, 155, 30);
		panelUserQuery.add(btnUserQuery);
		btnUserQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aEvent) {
				executeUserQuery(aEvent);
			}
		});
		
		scrUserQuery = new JScrollPane();
		scrUserQuery.setBounds(53, 52, 613, 246);
		panelUserQuery.add(scrUserQuery);

		txtUserQuery = new JTextArea();
		txtUserQuery.setFont(new Font("Calibri", Font.BOLD, 18));
		scrUserQuery.setViewportView(txtUserQuery);
		txtUserQuery.setEditable(false);

		srcUserResults = new JScrollPane();
		srcUserResults.setBounds(902, 45, 946, 482);
		panelUser.add(srcUserResults);
		
		modelUserResults = new DefaultTableModel();
		tblUserResults = new JTable();
		tblUserResults.setBackground(Color.LIGHT_GRAY);
		modelUserResults.addColumn("User Id");
		modelUserResults.addColumn("UserName");
		modelUserResults.addColumn("Yelping Since");
		modelUserResults.addColumn("Average Stars");
		tblUserResults.setModel(modelUserResults);
		tblUserResults.setBounds(1185, 40, 672, 746);
		srcUserResults.setViewportView(tblUserResults);
		tblUserResults.removeColumn(tblUserResults.getColumnModel().getColumn(0));
		slUserResults = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lEvent) {
				populateUserReviews(lEvent);
			}	
		};
		tblUserResults.getSelectionModel().addListSelectionListener(slUserResults);

		srcUserReviews = new JScrollPane();
		srcUserReviews.setBounds(900, 564, 948, 314);
		panelUser.add(srcUserReviews);
		
		modelUserReviews = new DefaultTableModel();
		tblUserReviews = new JTable();
		tblUserReviews.setBackground(Color.LIGHT_GRAY);
		modelUserReviews.addColumn("UserName");
		modelUserReviews.addColumn("Review Date");
		modelUserReviews.addColumn("Review Text");
		modelUserReviews.addColumn("Stars");
		modelUserReviews.addColumn("No. of Votes");
		tblUserReviews.setModel(modelUserReviews);
		tblUserReviews.setBounds(1185, 40, 672, 746);
		srcUserReviews.setViewportView(tblUserReviews);

	}

	/******************************************************BUSINESS SEARCH FUNCTIONS***********************************************************/
	/******************************************************************************************************************************************/

	//Method to populate the Main Category List------------------------------------------------------------------------------------------------
	public void populateMainCategory() {

		Connection conDB;
		String getMainCategories = "SELECT DISTINCT CATEGORY_NAME FROM MAIN_CATEGORIES ORDER BY CATEGORY_NAME\n";
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println(getMainCategories);
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		
		ResultSet rsMainCategories;
		try {

			conDB = DBConnection.getDBConnection();
			psQuery = conDB.prepareStatement(getMainCategories);
			rsMainCategories = psQuery.executeQuery();

			while(rsMainCategories.next()) {
				modelMainCat.addElement(new JCheckBox(rsMainCategories.getString("CATEGORY_NAME")));
			}

			psQuery.close();
			rsMainCategories.close();

			chkMainCategory.setModel(modelMainCat);

		}catch(Exception Ex) {
			Ex.printStackTrace();
		}		
	}

	//Action to be performed on the click of main category-------------------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	public void mainClicked(MouseEvent mEvent) {

		if(mEvent.getClickCount() == 1) {
			arrCheckedSubCat.clear();
			arrCheckedAttributes.clear();
			txtBusinessQuery.setText("");
			JList<JCheckBox> selectedMainCat = (JList<JCheckBox>) mEvent.getSource();
			ArrayList<JCheckBox> arrMainCat = (ArrayList<JCheckBox>) selectedMainCat.getSelectedValuesList();
			for(JCheckBox chk: arrMainCat) {
				if(chk.isSelected()){
					arrCheckedMainCat.add(chk.getText());
				}
				else {
					if(arrCheckedMainCat.size() != 0) {
						arrCheckedMainCat.remove(arrCheckedMainCat.indexOf(chk.getText()));
					}
				}
			}
			populateSubCategory(arrCheckedMainCat);
		}
	}


	//Method to populate Sub Category----------------------------------------------------------------------------------------------------------
	public void populateSubCategory(ArrayList<String> arrCheckedMainCat) {

		Connection conDB;
		modelSubCat.removeAllElements();
		chkSubCategory.setModel(modelSubCat);

		if(arrCheckedMainCat.size() != 0) {
			
			buildBusinessQuery(arrCheckedMainCat, arrCheckedSubCat, arrCheckedAttributes);

			String getSubCategories = "SELECT DISTINCT SC.CATEGORY_NAME FROM SUB_CATEGORIES SC, MAIN_CATEGORIES MC\r\n" + 
					"WHERE SC.BUSINESS_ID = MC.BUSINESS_ID AND MC.CATEGORY_NAME ";
			String strFinalQuery = getSubCategories;

			for(int i = 0; i< arrCheckedMainCat.size(); i++) {

				strFinalQuery += " LIKE '" + arrCheckedMainCat.get(i) + "' " + strAndOr + " " + getSubCategories;
			}

			strFinalQuery = strFinalQuery.substring(0, strFinalQuery.length() - (strAndOr.length() + getSubCategories.length() + 1));
			
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println(strFinalQuery);
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			ResultSet rsSubCategories = null;

			try {

				conDB = DBConnection.getDBConnection();
				psQuery = conDB.prepareStatement(strFinalQuery);
				rsSubCategories = psQuery.executeQuery();

				while(rsSubCategories.next()) {
					modelSubCat.addElement(new JCheckBox(rsSubCategories.getString("CATEGORY_NAME")));
				}

				psQuery.close();
				rsSubCategories.close();

				chkSubCategory.setModel(modelSubCat);

			}catch(Exception Ex) {
				Ex.printStackTrace();
			}
		}
		else {
			modelSubCat.removeAllElements();
			chkSubCategory.setModel(modelSubCat);
		}
	}


	//Action to be performed on the click of sub category--------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public void subClicked(MouseEvent mEvent) {

		if(mEvent.getClickCount() == 1) {
			arrCheckedAttributes.clear();
			txtBusinessQuery.setText("");
			JList<JCheckBox> selectedSubCat = (JList<JCheckBox>) mEvent.getSource();
			ArrayList<JCheckBox> arrSubCat = (ArrayList<JCheckBox>) selectedSubCat.getSelectedValuesList();
			for(JCheckBox chk : arrSubCat) {
				if(chk.isSelected()){
					arrCheckedSubCat.add(chk.getText());
				}
				else {
					if(arrCheckedSubCat.contains(chk.getText())) {
						arrCheckedSubCat.remove(arrCheckedSubCat.indexOf(chk.getText()));
					}
				}
			}
			populateAttributes(arrCheckedMainCat, arrCheckedSubCat);
		}
	}

	//Method is to populate Attributes---------------------------------------------------------------------------------------------------------
	public void populateAttributes(ArrayList<String> arrCheckedMainCat, ArrayList<String> arrCheckedSubCat) {

		Connection conDB;
		modelAttributes.removeAllElements();
		chkAttributes.setModel(modelAttributes);

		if(arrCheckedSubCat.size() != 0) {
			buildBusinessQuery(arrCheckedMainCat, arrCheckedSubCat, arrCheckedAttributes);
			String strFinalQuery = "";
			for(int i = 0; i < arrCheckedMainCat.size(); i++) {

				for(int j= 0; j < arrCheckedSubCat.size(); j++) {

					strFinalQuery += "SELECT DISTINCT BA.ATTRIBUTE_NAME FROM\r\n" + 
							"MAIN_CATEGORIES MC,  SUB_CATEGORIES SC, BUSINESS_ATTRIBUTES BA, BUSINESS BU\r\n" + 
							"WHERE BU.BUSINESS_ID = BA.BUSINESS_ID AND BU.BUSINESS_ID = SC.BUSINESS_ID AND MC.BUSINESS_ID = BU.BUSINESS_ID AND\r\n" + 
							"MC.CATEGORY_NAME LIKE '" + arrCheckedMainCat.get(i) + "' AND SC.CATEGORY_NAME LIKE '" + arrCheckedSubCat.get(j) + "'\n" + strAndOr + "\n";
				}
			}

			strFinalQuery = strFinalQuery.substring(0, strFinalQuery.length() - (strAndOr.length() + 1));

			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println(strFinalQuery);
			System.out.println("-------------------------------------------------------------------------------------------------------------------");

			ResultSet rsAttributes = null;

			try {

				conDB = DBConnection.getDBConnection();
				psQuery = conDB.prepareStatement(strFinalQuery);
				rsAttributes = psQuery.executeQuery();

				while(rsAttributes.next()) {
					modelAttributes.addElement(new JCheckBox(rsAttributes.getString("ATTRIBUTE_NAME")));
				}

				psQuery.close();
				rsAttributes.close();
				chkAttributes.setModel(modelAttributes);

			}catch(Exception Ex) {
				Ex.printStackTrace();
			}
		}
		else {
			modelAttributes.removeAllElements();
			chkAttributes.setModel(modelAttributes);
		}
	}

	//Action to be performed on the click of attribute-----------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public void attClicked(MouseEvent mEvent) {

		if(mEvent.getClickCount() == 1) {
			JList<JCheckBox> selectedAttributes = (JList<JCheckBox>) mEvent.getSource();
			ArrayList<JCheckBox> arrAttributes = (ArrayList<JCheckBox>) selectedAttributes.getSelectedValuesList();
			for(JCheckBox chk: arrAttributes) {
				if(chk.isSelected()){
					arrCheckedAttributes.add(chk.getText());
				}
				else {
					if(arrCheckedAttributes.size() != 0) {
						arrCheckedAttributes.remove(arrCheckedAttributes.indexOf(chk.getText()));
					}
				}
			}
			buildBusinessQuery(arrCheckedMainCat, arrCheckedSubCat, arrCheckedAttributes);
		}
	}


	//Method is to build the final Query for Business Search to display Results----------------------------------------------------------------
	public void buildBusinessQuery(ArrayList<String> arrCheckedMainCat, ArrayList<String> arrCheckedSubCat, ArrayList<String> arrCheckedAttributes) {

		String businessQuery = "", strFinalQuery = "";
		
		//check if any of the attributes from the reviews tab is selected
		if(cmbBusinessFromDate.getDate() != null || cmbBusinessToDate.getDate() != null || !(txtBusinessStarValue.getText().isEmpty()) || !(txtBusinessVotesValue.getText().isEmpty())) {
			
			businessQuery = "SELECT DISTINCT BU.BUSINESS_ID, BU.BUSINESS_NAME, BU.CITY, BU.STATE, BU.STARS FROM BUSINESS BU, " + ""
					+ "REVIEWS R WHERE BU.BUSINESS_ID = R.BUSINESS_ID ";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strFrom;
			String strTo;
			
			if(cmbBusinessFromDate.getDate() != null && cmbBusinessToDate.getDate() == null) {
				strFrom = sdf.format(cmbBusinessFromDate.getDate());
				businessQuery += "  AND R.REVIEW_DATE > '" + strFrom + "' AND ";
			}
			if(cmbBusinessToDate.getDate() != null && cmbBusinessFromDate.getDate() == null) {
				strTo = sdf.format(cmbBusinessToDate.getDate());
				businessQuery += "  AND R.REVIEW_DATE < '" + strTo + "' AND ";
			}
			if(cmbBusinessFromDate.getDate() != null && cmbBusinessToDate.getDate() != null) {
				strFrom = sdf.format(cmbBusinessFromDate.getDate());
				strTo = sdf.format(cmbBusinessToDate.getDate());
				businessQuery += "  AND R.REVIEW_DATE BETWEEN '" + strFrom + "' AND '" + strTo + "' AND ";
			}
			
			if(!(txtBusinessStarValue.getText().isEmpty())) {
				businessQuery += "  AND BU.STARS " + cmbBusinessStar.getSelectedItem().toString() + " '" + txtBusinessStarValue.getText() + "' AND ";
			}
			
			if(!(txtBusinessVotesValue.getText().isEmpty())) {
				businessQuery += "  AND (R.VOTES_COOL + R.VOTES_FUNNY + R.VOTES_USEFUL) " + cmbBusinessVotes.getSelectedItem().toString() + " '" + txtBusinessVotesValue.getText() + "' AND ";
			}
			
		}else {
			businessQuery = "SELECT DISTINCT BU.BUSINESS_ID, BU.BUSINESS_NAME, BU.CITY, BU.STATE, BU.STARS FROM BUSINESS BU WHERE";
		}
		

		//Query if only Main Category is Selected
		if(arrCheckedMainCat.size() != 0 && arrCheckedSubCat.size() == 0 && arrCheckedAttributes.size() == 0) {

			for (int i = 0; i <  arrCheckedMainCat.size(); i++) {

				strFinalQuery += businessQuery + "\n BU.BUSINESS_ID IN\r\n" + 
						"(SELECT MC.BUSINESS_ID FROM MAIN_CATEGORIES MC WHERE MC.CATEGORY_NAME LIKE '" + arrCheckedMainCat.get(i) + "')\n" + strAndOr + "\n";
			}
		}

		//Query if Main and Sub Categories are selected
		if(arrCheckedMainCat.size() != 0 && arrCheckedSubCat.size() != 0 && arrCheckedAttributes.size() == 0) {

			for(int i = 0; i <  arrCheckedMainCat.size(); i++) {

				for(int j = 0; j <  arrCheckedSubCat.size(); j++) {

					strFinalQuery += businessQuery + "\n BU.BUSINESS_ID IN\r\n" + 
							"(SELECT BU.BUSINESS_ID FROM MAIN_CATEGORIES MC,  SUB_CATEGORIES SC, BUSINESS_ATTRIBUTES BA, BUSINESS BU\r\n" + 
							"WHERE BU.BUSINESS_ID = BA.BUSINESS_ID AND BU.BUSINESS_ID = SC.BUSINESS_ID AND MC.BUSINESS_ID = BU.BUSINESS_ID AND\r\n" + 
							"MC.CATEGORY_NAME LIKE '" + arrCheckedMainCat.get(i) + "' AND SC.CATEGORY_NAME LIKE '" + arrCheckedSubCat.get(j) + "' )\n" + strAndOr + "\n";

				}
			}
		}

		//Query if Main and Sub Categories and attributes are selected
		if(arrCheckedMainCat.size() != 0 && arrCheckedSubCat.size() != 0 && arrCheckedAttributes.size() != 0) {

			for(int i = 0; i <  arrCheckedMainCat.size(); i++) {

				for(int j = 0; j <  arrCheckedSubCat.size(); j++) {

					for(int k = 0; k <  arrCheckedAttributes.size(); k++) {

						strFinalQuery += businessQuery + "\n BU.BUSINESS_ID IN\r\n" + 
								"(SELECT BU.BUSINESS_ID FROM MAIN_CATEGORIES MC,  SUB_CATEGORIES SC, BUSINESS_ATTRIBUTES BA, BUSINESS BU\r\n" + 
								"WHERE BU.BUSINESS_ID = BA.BUSINESS_ID AND BU.BUSINESS_ID = SC.BUSINESS_ID AND MC.BUSINESS_ID = BU.BUSINESS_ID AND\r\n" + 
								"MC.CATEGORY_NAME LIKE '" + arrCheckedMainCat.get(i) + "' AND SC.CATEGORY_NAME LIKE '" + arrCheckedSubCat.get(j) + "' and BA.ATTRIBUTE_NAME LIKE '" + 
								arrCheckedAttributes.get(k) + "' )\n" + strAndOr + "\n";

					}	
				}
			}
		}

		strFinalQuery = strFinalQuery.substring(0, strFinalQuery.length() - (strAndOr.length() + 1));
		txtBusinessQuery.setText(strFinalQuery);
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println(strFinalQuery);
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
	}


	//Method to populate results in results in result table------------------------------------------------------------------------------------
	public void populateResults(ActionEvent aEvent) {

		Connection conDB = null;
		String strFinalQuery = "";
		if(slBusinessResults != null) {	tblBusinessResults.getSelectionModel().removeListSelectionListener(slBusinessResults);}
		
		for(int i = 0; i < modelBusinessResults.getRowCount(); i++) {
			modelBusinessResults.removeRow(i);
		}
		
		String[] rowObj = new String[4];
		strFinalQuery = txtBusinessQuery.getText();
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println(strFinalQuery);
		System.out.println("-------------------------------------------------------------------------------------------------------------------");

		ResultSet rsResults = null;

		try {

			conDB = DBConnection.getDBConnection();
			psQuery = conDB.prepareStatement(strFinalQuery);
			rsResults = psQuery.executeQuery();

			if(rsResults != null) {
				while(rsResults.next()) {
					rowObj = new String[] {rsResults.getString("BUSINESS_ID"), rsResults.getString("BUSINESS_NAME"), rsResults.getString("CITY"), rsResults.getString("STATE"), rsResults.getString("STARS")};					
					modelBusinessResults.addRow(rowObj);			
				}
			}else {
				String strNoResults = "No Results Found.";
				rowObj = new String[] {strNoResults, "", "", ""};
				modelBusinessResults.addRow(rowObj);
			}

			psQuery.close();
			rsResults.close();

		}catch(Exception Ex) {
			Ex.printStackTrace();
		}
		tblBusinessResults.getSelectionModel().addListSelectionListener(slBusinessResults);
	}

	//Method is to populate the reviews for the business selected from the results table
	public void populateBusinessReviews(ListSelectionEvent lEvent) {
		
		Connection conDB = null;
		String strFinalQuery = "SELECT YU.USERNAME, R.REVIEW_DATE, R.REVIEW_TEXT, R.STARS, (R.VOTES_COOL + R.VOTES_FUNNY + R.VOTES_USEFUL) AS No_Of_Votes FROM REVIEWS R, YELP_USER YU\r\n" + 
				"WHERE R.USER_ID = YU.USER_ID AND R.BUSINESS_ID = ";
		
		String[] rowObj = new String[5];
		
		if (modelBusinessReviews.getRowCount() > 0) {
		    for (int i = modelBusinessReviews.getRowCount() - 1; i > -1; i--) {
		    	modelBusinessReviews.removeRow(i);
		    }
		}
		
		if(!lEvent.getValueIsAdjusting()) {
			String strBusinessId = (String) tblBusinessResults.getModel().getValueAt(tblBusinessResults.getSelectedRow(), 0);
			
			strFinalQuery += "'" + strBusinessId + "'";
			
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println(strFinalQuery);
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			
			ResultSet rsBusinessReviews = null;

			try {
				conDB = DBConnection.getDBConnection();
				psQuery = conDB.prepareStatement(strFinalQuery);
				rsBusinessReviews = psQuery.executeQuery();

				if(rsBusinessReviews != null) {
					while(rsBusinessReviews.next()) {

						rowObj = new String[] {rsBusinessReviews.getString("USERNAME"), rsBusinessReviews.getString("REVIEW_DATE"), rsBusinessReviews.getString("REVIEW_TEXT"), rsBusinessReviews.getString("STARS"), rsBusinessReviews.getString("No_Of_Votes")};					
						modelBusinessReviews.addRow(rowObj);
					}
				}else {
					String strNoResults = "No Results Found.";
					rowObj = new String[] {strNoResults, "", "", ""};
					modelBusinessReviews.addRow(rowObj);
				}
				psQuery.close();
				rsBusinessReviews.close();

			}catch(Exception Ex) {
				Ex.printStackTrace();
			}	
		}
		
	}
	
	//Method to get AND-OR selection for search criteria---------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public void selectAndOr(ActionEvent aEvent) {

		JComboBox<String> cmbSelected = (JComboBox<String>) aEvent.getSource();
		String strSelected = (String) cmbSelected.getSelectedItem();
		if(strSelected.equals("OR")) {strAndOr = "UNION";}
		else {strAndOr = "INTERSECT";}
	}

	//Code snippet for creating JCheckBoxList--------------------------------------------------------------------------------------------------
	public class JCheckBoxList extends JList<JCheckBox> {
		protected Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

		public JCheckBoxList() {
			setCellRenderer(new CellRenderer());
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					int index = locationToIndex(e.getPoint());
					if (index != -1) {
						JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
						checkbox.setSelected(!checkbox.isSelected());
						repaint();
					}
				}
			});
			setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		public JCheckBoxList(ListModel<JCheckBox> model){
			this();
			setModel(model);
		}

		protected class CellRenderer implements ListCellRenderer<JCheckBox> {
			public Component getListCellRendererComponent(
					JList<? extends JCheckBox> list, JCheckBox value, int index,
					boolean isSelected, boolean cellHasFocus) {
				JCheckBox checkbox = value;
				checkbox.setBackground(isSelected ? getSelectionBackground()
						: getBackground());
				checkbox.setForeground(isSelected ? getSelectionForeground()
						: getForeground());
				checkbox.setEnabled(isEnabled());
				checkbox.setFont(getFont());
				checkbox.setFocusPainted(false);
				checkbox.setBorderPainted(true);
				checkbox.setBorder(isSelected ? UIManager
						.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
				return checkbox;
			}
		}
	}

	/******************************************************END OF BUSINESS SEARCH FUNCTIONS****************************************************/
	/******************************************************************************************************************************************/
	
	/**********************************************************USER SEARCH FUNCTIONS***********************************************************/
	/******************************************************************************************************************************************/

	//Method is to build the User Search Query using the selected attributes
	public void buildUserQuery(ActionEvent aEvent) {
			
		String strMemberSince = "", strReviewCount = "", strReviewCountValue = "", strNoOfFriends = "", strNoOfFriendsValue = "";
		String strAvgStars = "", strAvgStarsValue = "", strNoOfVotes = "", strNoOfVotesValues = "", strUserAndOr = "";
			
		strUserAndOr = cmbUserAndOr.getSelectedItem().toString();
		
		String strFinalQuery = "";
		
		if(!txtFriendsValue.getText().isEmpty()) {

			strFinalQuery = "SELECT YU.USER_ID, YU.USERNAME, YU.YELPING_SINCE, YU.AVERAGE_STARS FROM YELP_USER YU,\r\n" + 
					"(SELECT F.USER_ID, COUNT(F.FRIEND_ID) AS No_OF_Friends  FROM  FRIENDS F\r\n" + 
					"GROUP BY F.USER_ID) FR\r\n" + 
					"WHERE FR.USER_ID = YU.USER_ID  " + strUserAndOr;

			strNoOfFriendsValue = txtFriendsValue.getText();
			strNoOfFriends = cmbFriends.getSelectedItem().toString();
			strFinalQuery += "  FR.No_OF_Friends " + strNoOfFriends + " '" + strNoOfFriendsValue + "' " + strUserAndOr;
		}else {
			strFinalQuery = "SELECT YU.USER_ID, YU.USERNAME, YU.YELPING_SINCE, YU.AVERAGE_STARS FROM YELP_USER YU WHERE ";
		}
		
		if(cmbMemberSince.getDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			strMemberSince = sdf.format(cmbMemberSince.getDate());
			strFinalQuery += "  YU.YELPING_SINCE >= '" + strMemberSince + "' " + strUserAndOr;
		}

		if(!txtReviewCountValue.getText().isEmpty()) {
			strReviewCountValue = txtReviewCountValue.getText();
			strReviewCount = cmbReviewCount.getSelectedItem().toString();
			strFinalQuery += "  YU.REVIEWS_COUNT " + strReviewCount + " '" + strReviewCountValue + "' " + strUserAndOr;
		}



		if(!txtAverageStarsValue.getText().isEmpty()) {
			strAvgStarsValue = txtAverageStarsValue.getText();
			strAvgStars = cmbAverageStars.getSelectedItem().toString();
			strFinalQuery += "  YU.AVERAGE_STARS " + strAvgStars + " '" + strAvgStarsValue + "' " + strUserAndOr;
		}

		if(!txtVotesValue.getText().isEmpty()) {
			strNoOfVotesValues = txtVotesValue.getText();
			strNoOfVotes = cmbVotes.getSelectedItem().toString();
			strFinalQuery += "  (YU.VOTES_COOL + YU.VOTES_FUNNY + YU.VOTES_USEFUL) " + strNoOfVotes + " '" + strNoOfVotesValues + "' " + strUserAndOr;
		}
		
		strFinalQuery = strFinalQuery.substring(0, strFinalQuery.length() - strUserAndOr.length());
		strFinalQuery += "\n ORDER BY YU.USERNAME";
		txtUserQuery.setText(strFinalQuery);
		
	}

	//Method is to execute the user search query
	public void executeUserQuery(ActionEvent aEvent) {
		
		Connection conDB = null;
		String strFinalQuery ="";
		tblUserResults.getSelectionModel().removeListSelectionListener(slUserResults);
		if(!txtUserQuery.getText().isEmpty()) {
			strFinalQuery = txtUserQuery.getText();
		}
		String[] rowObj = new String[4];
		
		if (modelUserResults.getRowCount() > 0) {
		    for (int i = modelUserResults.getRowCount() - 1; i > -1; i--) {
		    	modelUserResults.removeRow(i);
		    }
		}
		
		ResultSet rsUserResults = null;

		try {

			conDB = DBConnection.getDBConnection();
			psQuery = conDB.prepareStatement(strFinalQuery);
			rsUserResults = psQuery.executeQuery();

			if(rsUserResults != null) {
				while(rsUserResults.next()) {

					rowObj = new String[] {rsUserResults.getString("USER_ID"), rsUserResults.getString("USERNAME"), rsUserResults.getString("YELPING_SINCE"), rsUserResults.getString("AVERAGE_STARS")};					
					modelUserResults.addRow(rowObj);
				}
			}else {
				String strNoResults = "No Results Found.";
				rowObj = new String[] {strNoResults, "", "", ""};
				modelUserResults.addRow(rowObj);
			}
			psQuery.close();
			rsUserResults.close();

		}catch(Exception Ex) {
			Ex.printStackTrace();
		}	
		
		tblUserResults.getSelectionModel().addListSelectionListener(slUserResults);
		
	}
	
	//Method is to populate the reviews for the user selected from the results table
	public void populateUserReviews(ListSelectionEvent lEvent) {
		
		Connection conDB = null;
		String strFinalQuery = "SELECT YU.USERNAME, R.REVIEW_DATE, R.REVIEW_TEXT, R.STARS, (R.VOTES_COOL + R.VOTES_FUNNY + R.VOTES_USEFUL) AS No_Of_Votes FROM REVIEWS R, YELP_USER YU\r\n" + 
				"WHERE R.USER_ID = YU.USER_ID AND R.USER_ID = ";
		String[] rowObj = new String[5];
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println(strFinalQuery);
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		
		if (modelUserReviews.getRowCount() > 0) {
		    for (int i = modelUserReviews.getRowCount() - 1; i > -1; i--) {
		    	modelUserReviews.removeRow(i);
		    }
		}
		
		if(!lEvent.getValueIsAdjusting()) {
			String strUserId = (String) tblUserResults.getModel().getValueAt(tblUserResults.getSelectedRow(), 0);
			
			strFinalQuery += "'" + strUserId + "'";
			
			ResultSet rsUserReviews = null;

			try {

				conDB = DBConnection.getDBConnection();
				psQuery = conDB.prepareStatement(strFinalQuery);
				rsUserReviews = psQuery.executeQuery();

				if(rsUserReviews != null) {
					while(rsUserReviews.next()) {

						rowObj = new String[] {rsUserReviews.getString("USERNAME"), rsUserReviews.getString("REVIEW_DATE"), rsUserReviews.getString("REVIEW_TEXT"), rsUserReviews.getString("STARS"), rsUserReviews.getString("No_Of_Votes")};					
						modelUserReviews.addRow(rowObj);
					}
				}else {
					String strNoResults = "No Results Found.";
					rowObj = new String[] {strNoResults, "", "", ""};
					modelUserResults.addRow(rowObj);
				}
				psQuery.close();
				rsUserReviews.close();

			}catch(Exception Ex) {
				Ex.printStackTrace();
			}	
		}
	}
	
	/********************************************************END OF USER SEARCH FUNCTIONS******************************************************/
	/******************************************************************************************************************************************/
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		HW3 t = new HW3();
		t.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		t.pack();
		t.setSize(screenSize);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setResizable(false);
		t.setLocationRelativeTo(null);
	}
	}
