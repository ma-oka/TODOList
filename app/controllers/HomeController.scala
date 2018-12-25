package controllers

import javax.inject._
import play.api.data._
import play.api.data.Forms._
import play.api.db.Database
import play.api.mvc.{AbstractController, ControllerComponents}
import models.Task

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(db: Database, cc: ControllerComponents)
    extends AbstractController(cc)
    with play.api.i18n.I18nSupport {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action { Redirect(routes.HomeController.tasks) }

  def hello(name: String) = Action {
    Ok(views.html.hello(name))
  }

  val taskForm: Form[String] = Form("label" -> nonEmptyText)

  def tasks = Action { implicit request =>
    Ok(views.html.index(Task.all(db), taskForm))
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(db), errors)),
      label => {
        Task.create(db, label)
        Redirect(routes.HomeController.tasks)
      }
    )
  }

  def deleteTask(id: Long) = Action {
    Task.delete(db, id)
    Redirect(routes.HomeController.tasks)
  }
}
