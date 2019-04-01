package com.siliconvalleyoffice.git4jira.model

/**
 * Board Response
 */
data class BoardResponse(
    val isLast: Boolean,
    val maxResults: Int,
    val startAt: Int,
    val values: List<Board>
)

data class Board(
    val id: Int,
    val name: String,
    val self: String,
    val type: String
)

/**
 * Epic Response
 */
data class EpicResponse(
    val isLast: Boolean,
    val maxResults: Int,
    val startAt: Int,
    val values: List<Epic>
)

data class Epic(
    val color: Color,
    val done: Boolean,
    val id: Int,
    val key: String,
    val name: String,
    val self: String,
    val summary: String
)

data class Color(
    val key: String
)

/**
 * Issues Response
 */
data class IssuesResponse(
    val expand: String,
    val issues: List<Issue>,
    val maxResults: Int,
    val startAt: Int,
    val total: Int
)

data class Issue(
    val expand: String,
    val fields: Fields,
    val id: String,
    val key: String,
    val self: String
)

data class Fields(
    val aggregateprogress: Aggregateprogress,
    val aggregatetimeestimate: Any,
    val aggregatetimeoriginalestimate: Any,
    val aggregatetimespent: Any,
    val assignee: Assignee,
    val attachment: List<Attachment>,
    val comment: Comment,
    val components: List<Any>,
    val created: String,
    val creator: Creator,
    val description: String,
    val duedate: Any,
    val environment: Any,
    val epic: Epic,
    val fixVersions: List<Any>,
    val flagged: Boolean,
    val issuelinks: List<Issuelink>,
    val issuetype: Issuetype,
    val labels: List<String>,
    val lastViewed: Any,
    val priority: Priority,
    val progress: Progress,
    val project: IssueProject,
    val reporter: Reporter,
    val resolution: Any,
    val resolutiondate: Any,
    val status: Status,
    val subtasks: List<Any>,
    val summary: String,
    val timeestimate: Any,
    val timeoriginalestimate: Any,
    val timespent: Any,
    val updated: String,
    val versions: List<Version>,
    val votes: Votes,
    val watches: Watches,
    val worklog: Worklog,
    val workratio: Int
)

data class Assignee(
    val active: Boolean,
    val displayName: String,
    val emailAddress: String,
    val key: String,
    val name: String,
    val self: String,
    val timeZone: String
)

data class Watches(
    val isWatching: Boolean,
    val self: String,
    val watchCount: Int
)

data class Version(
    val archived: Boolean,
    val id: String,
    val name: String,
    val released: Boolean,
    val self: String
)

data class Status(
    val description: String,
    val iconUrl: String,
    val id: String,
    val name: String,
    val self: String,
    val statusCategory: StatusCategory
)

data class StatusCategory(
    val colorName: String,
    val id: Int,
    val key: String,
    val name: String,
    val self: String
)

data class Comment(
    val comments: List<CommentX>,
    val maxResults: Int,
    val startAt: Int,
    val total: Int
)

data class CommentX(
    val author: Author,
    val body: String,
    val created: String,
    val id: String,
    val self: String,
    val updateAuthor: UpdateAuthor,
    val updated: String
)

data class Author(
    val active: Boolean,
    val displayName: String,
    val emailAddress: String,
    val key: String,
    val name: String,
    val self: String,
    val timeZone: String
)

data class UpdateAuthor(
    val active: Boolean,
    val displayName: String,
    val emailAddress: String,
    val key: String,
    val name: String,
    val self: String,
    val timeZone: String
)

data class Progress(
    val progress: Int,
    val total: Int
)

data class Reporter(
    val active: Boolean,
    val displayName: String,
    val emailAddress: String,
    val key: String,
    val name: String,
    val self: String,
    val timeZone: String
)

data class Worklog(
    val maxResults: Int,
    val startAt: Int,
    val total: Int,
    val worklogs: List<Any>
)

data class Priority(
    val iconUrl: String,
    val id: String,
    val name: String,
    val self: String
)

data class IssueProject(
    val id: String,
    val key: String,
    val name: String,
    val projectCategory: ProjectCategory,
    val self: String
)

data class ProjectCategory(
    val description: String,
    val id: String,
    val name: String,
    val self: String
)

data class Creator(
    val active: Boolean,
    val displayName: String,
    val emailAddress: String,
    val key: String,
    val name: String,
    val self: String,
    val timeZone: String
)

data class Issuetype(
    val avatarId: Int,
    val description: String,
    val iconUrl: String,
    val id: String,
    val name: String,
    val self: String,
    val subtask: Boolean
)

data class Votes(
    val hasVoted: Boolean,
    val self: String,
    val votes: Int
)

data class Attachment(
    val author: Author,
    val content: String,
    val created: String,
    val filename: String,
    val id: String,
    val mimeType: String,
    val self: String,
    val size: Int,
    val thumbnail: String
)

data class Issuelink(
    val id: String,
    val inwardIssue: InwardIssue,
    val outwardIssue: OutwardIssue,
    val self: String,
    val type: Type
)

data class InwardIssue(
    val fields: FieldsX,
    val id: String,
    val key: String,
    val self: String
)

data class FieldsX(
    val issuetype: Issuetype,
    val priority: Priority,
    val status: Status,
    val summary: String
)

data class OutwardIssue(
    val fields: Fields,
    val id: String,
    val key: String,
    val self: String
)

data class Type(
    val id: String,
    val inward: String,
    val name: String,
    val outward: String,
    val self: String
)

data class Aggregateprogress(
    val progress: Int,
    val total: Int
)