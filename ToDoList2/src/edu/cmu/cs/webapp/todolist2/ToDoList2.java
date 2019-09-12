package edu.cmu.cs.webapp.todolist2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.cmu.cs.webapp.todolist2.dao.ItemDAO;
import edu.cmu.cs.webapp.todolist2.dao.MyDAOException;
import edu.cmu.cs.webapp.todolist2.dao.UserDAO;
import edu.cmu.cs.webapp.todolist2.databean.ItemBean;
import edu.cmu.cs.webapp.todolist2.databean.User;
import edu.cmu.cs.webapp.todolist2.formbean.IdForm;
import edu.cmu.cs.webapp.todolist2.formbean.ItemForm;
import edu.cmu.cs.webapp.todolist2.formbean.LoginForm;

public class ToDoList2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ItemDAO itemDAO = new ItemDAO();
	private UserDAO userDAO = new UserDAO();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			outputLoginPage(response, null, null);
		} else {
			outputToDoList(response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			login(request, response);
		} else {
			manageList(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();

		LoginForm form = new LoginForm(request);

		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) {
			outputLoginPage(response, form, errors);
			return;
		}

		try {
			User user;

			if (form.getButton().equals("register")) {
				user = new User();
				user.setUserName(form.getUserName());
				user.setPassword(form.getPassword());
				userDAO.create(user);
			} else {
				user = userDAO.read(form.getUserName());
				if (user == null) {
					errors.add("No such user");
					outputLoginPage(response, form, errors);
					return;
				}

				if (!form.getPassword().equals(user.getPassword())) {
					errors.add("Incorrect password");
					outputLoginPage(response, form, errors);
					return;
				}
			}

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			outputToDoList(response);
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			outputLoginPage(response, form, errors);
		}
	}

	private void manageList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Look at the action parameter to see what we're doing to the list
		String action = request.getParameter("action");

		if (action == null) {
			outputToDoList(response, "No action specified.");
			return;
		}

		if (action.equals("delete")) {
			processDelete(request, response);
			return;
		}

		if (action.equals("top")) {
			processAdd(request, response, true);
			return;
		}

		if (action.equals("bottom")) {
			processAdd(request, response, false);
			return;
		}

        if (action.equals("logout")) {
            processLogout(request, response);
            return;
        }

		outputToDoList(response, "Invalid action: " + action);
	}

	private void processAdd(HttpServletRequest request, HttpServletResponse response, boolean addToTop)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();

		ItemForm form = new ItemForm(request);

		errors.addAll(form.getValidationErrors());
		if (errors.size() > 0) {
			outputToDoList(response, errors);
			return;
		}

		try {
			ItemBean bean = new ItemBean();
			bean.setItem(form.getItem());
			bean.setIpAddress(request.getRemoteAddr());
			User u = (User) request.getSession().getAttribute("user");
			bean.setUserName(u.getUserName());
			if (addToTop) {
				itemDAO.addToTop(bean);
			} else {
				itemDAO.addToBottom(bean);
			}
			outputToDoList(response, "Item Added");
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			outputToDoList(response, errors);
		}
	}

	private void processDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();

		IdForm form = new IdForm(request);
		errors.addAll(form.getValidationErrors());
		if (errors.size() > 0) {
			outputToDoList(response, errors);
			return;
		}

		try {
			itemDAO.delete(form.getIdAsInt());
			outputToDoList(response, "Item Deleted");
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			outputToDoList(response, errors);
		}
	}
	
	private void processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	    
	    request.getSession().removeAttribute("user");
        outputLoginPage(response, null, null);
    }

	// Methods that generate & output HTML
	
	private String formatErrors(List<String> errors) {
        if (errors == null) {
            return "";
        }

        StringBuilder b = new StringBuilder();
	    
	    for (String error : errors) {
	        b.append("<p class=\"error\">");
	        b.append(error);
	        b.append("</p>");
	    }
	    
	    return b.toString();
	}
	
    private String formatList(ItemBean[] beans) throws IOException {
        String listItemTemplate = readTextFile("list-item.html");
        
        StringBuilder builder = new StringBuilder();
        
        for (ItemBean bean : beans) {
            Map<String,String> context = new HashMap<String,String>();
            context.put("$id$", String.valueOf(bean.getId()));
            context.put("$text$", bean.getItem());
            context.put("$user$", bean.getUserName());
            context.put("$ipAddress$", bean.getIpAddress());
            
            String listItem = insertContext(listItemTemplate, context);
            builder.append(listItem);
        }
        
        return builder.toString();
    }

	private void outputLoginPage(HttpServletResponse response, LoginForm form, List<String> errors) throws IOException {
	    Map<String,String> context = new HashMap<String,String>();
	    
	    context.put("$error-messages$", formatErrors(errors));
	    
	    if (form != null && form.getUserName() != null) {
	        context.put("$userName$", form.getUserName());
	    } else {
	        context.put("$userName$", "");
	    }

	    sendTextFile(response, "login.html", context);
	}

	private void outputToDoList(HttpServletResponse response)
			throws IOException {
		// Just call the version that takes a List passing an empty List
		List<String> list = new ArrayList<String>();
		outputToDoList(response, list);
	}

	private void outputToDoList(HttpServletResponse response, String message)
			throws IOException {
		// Put the message into a List and call the version that takes a List
		List<String> list = new ArrayList<String>();
		list.add(message);
		outputToDoList(response, list);
	}

	private void outputToDoList(HttpServletResponse response, List<String> messages) throws IOException {
		// Get the list of items to display at the end
		ItemBean[] beans;
		try {
			beans = itemDAO.getItems();
		} catch (MyDAOException e) {
			// If there's an access error, add the message to our list of messages
			messages.add(e.getMessage());
			beans = new ItemBean[0];
		}

	    Map<String,String> context = new HashMap<String,String>();
	        
	    context.put("$error-messages$", formatErrors(messages));
	    context.put("$num$", String.valueOf(beans.length));
	    context.put("$list$", formatList(beans));
	    
	    sendTextFile(response, "index.html", context);

	}
	
	   
    private String insertContext(String html, Map<String,String> context) {
        StringBuilder b = new StringBuilder(html);
        for (String key : context.keySet()) {
            int startPos = b.indexOf(key);
            while (startPos != -1) {
                int endPos = startPos + key.length();
                String value = context.get(key);
                b.replace(startPos, endPos, value);
                startPos = b.indexOf(key);
            }
        }
        return b.toString();
    }

    private void sendTextFile(HttpServletResponse response, String fileName, Map<String,String> context) throws IOException {
        String originalHtml = readTextFile(fileName);

        if (context == null) {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(originalHtml);
            return;
        }
            
        String updatedHtml = insertContext(originalHtml, context);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(updatedHtml);
    }

    private String readTextFile(String fileName) throws IOException {
        InputStream is = getServletContext().getResourceAsStream(fileName);
        if (is == null) {
            throw new IOException("File not found: " + fileName);
        }
    
        return streamToString(is);
    }

    private String streamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            StringBuilder b = new StringBuilder();

            String line = br.readLine();
            while (line != null) {
                b.append(line);
                b.append('\n');
                line = br.readLine();
            }

            return b.toString();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
