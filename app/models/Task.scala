package models

import play.api.db._
import anorm._
import anorm.SqlParser._

case class Task(id: Long, label: String)

object Task {
  val task =
    get[Long]("id") ~
      get[String]("label") map {
      case id ~ label => Task(id, label)
    }

  def all(db: Database): List[Task] = db.withConnection { implicit c =>
    SQL("select * from task").as(task *)
  }

  def create(db: Database, label: String) =
    db.withConnection { implicit c =>
      SQL("insert into task(label)values({label})")
        .on('label -> label)
        .executeUpdate()
    }

  def delete(db: Database, id: Long) =
    db.withConnection { implicit c =>
      SQL("delete from task where id = {id}")
        .on('id -> id)
        .executeUpdate()
    }
}
