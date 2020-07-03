<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap.min.css">
    <title>Manage book</title>
</head>

<body class="bg-secondary">
    <div class="container bg-light mt-5 px-5 pb-5 rounded">
        <!-- HEADING -->
        <div class="row">
            <div class="col-12 mt-5 d-flex align-items-center justify-content-between">
                <h1 class="text-primary">Quản lý sách</h1>
                <button class="btn btn-dark"><i class="fas fa-plus"></i>&nbsp;Add</button>
            </div>
        </div>
        
        <div class="row">
            <div class="col-12 text-center">
                <div class="btn-group-sm">
                    <a href="#" class="btn btn-light">Book</a>
                    <a href="#" class="btn btn-secondary">Cateory</a>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-12">
                <div class="alert alert-info collapse show active" role="alert">
                  A simple alert—check it out!
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="col-md-6">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="dropdown">
                              <button class="btn btn-dark dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Search by title
                              </button>
                              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item" href="#">Search by title</a>
                                <a class="dropdown-item" href="#">Search by author</a>
                              </div>
                            </div>
                        </div>
                        <input type="text" class="form-control" name="search">
                        <div class="input-group-append">
                            <button class="btn btn-primary">Search</button>
                        </div>
                    </div>
                        
                </div>
            </div>
        </div>
        <!-- TABLE -->
        <div class="row">
            <div class="col-12">
                <table class="table table-hover mt-5">
                    <caption>List of books</caption>
                    <thead class="thead-dark|thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Title</th>
                            <th scope="col">Author</th>
                            <th scope="col">Category</th>
                            <th scope="col">Price</th>
                            <th scope="col">Action</th>
                            <th scope="col">Id</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Naoko</td>
                            <td>Higashino Keigo</td>
                            <td>Trinh thám</td>
                            <td>87000</td>
                            <td>
                                <a href="#" class="text-success"><i class="fas fa-edit fa-fw"></i></a>
                                <a href="#" class="text-danger"><i class="fas fa-trash-alt fa-fw"></i></a>
                            </td>
                            <td>1</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- SCRIPTS -->
    <script type="text/javascript" src="./jquery/jquery.min.js"></script>
    <script type="text/javascript" src="./bootstrap/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./fontawesome-free/js/all.js"></script>
</body>

</html>